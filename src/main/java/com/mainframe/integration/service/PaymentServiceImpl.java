package com.mainframe.integration.service;
import com.mainframe.integration.messaging.PaymentProducer;
import com.mainframe.integration.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import com.mainframe.integration.service.PaymentRepository;
import com.mainframe.integration.service.PaymentAuditRepository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import com.mainframe.integration.messaging.PaymentProducer;
@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentRepository paymentRepository;
    private final PaymentAuditRepository paymentAuditRepository;
    private final PaymentProducer paymentProducer;
    private final Counter paymentCounter;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              PaymentAuditRepository paymentAuditRepository,
                              PaymentProducer paymentProducer,
                              MeterRegistry meterRegistry) {
        this.paymentRepository = paymentRepository;
        this.paymentAuditRepository = paymentAuditRepository;
        this.paymentProducer = paymentProducer;
        this.paymentCounter = Counter.builder("Payments_processed_total")
                .description("Total payments Processed")
                .register(meterRegistry);
    }

    @Override
    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {

        logger.info("Creating payment for customer {}", request.getCustomerId());

        String transactionId = "TXN-" + UUID.randomUUID();

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return new PaymentResponse(transactionId, "REJECTED");
        }

        if (request.getAmount().compareTo(new BigDecimal("100000")) > 0 ) {
            return new PaymentResponse(transactionId,"LIMIT_EXCEEDED");
        }

        System.out.println("Customer: " + request.getCustomerId());
        System.out.println("Account: " + request.getAccountNumber());
        System.out.println("Amount: " + request.getAmount());
        System.out.println("Currency: " +request.getCurrency());

        Payment payment = new Payment();
        payment.setCustomerId(request.getCustomerId());
        payment.setAccountNumber(request.getAccountNumber());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());

       /* if (request.getAmount().compareTo(new BigDecimal("15000")) == 0) {
            throw new RuntimeException("Testing generic exception handling");
        } */
        paymentRepository.save(payment);

        /*paymentProducer.sendMessage("Payment Created : " + payment.getId());*/
        PaymentEvent event = new PaymentEvent();
        event.setPaymentId(payment.getId());
        event.setCustomerId(payment.getCustomerId());
        event.setAmount(payment.getAmount());
        event.setStatus("PAYMENT_CREATED");

        paymentProducer.sendMessage(event);

        PaymentAudit audit = new PaymentAudit();
        audit.setPaymentId(payment.getId());
        audit.setAction("PAYMENT_CREATED");
        audit.setAuditTime(LocalDateTime.now());

        paymentAuditRepository.save(audit);
        paymentCounter.increment();

        /*throw new RuntimeException("Testing transaction Rollback"); */
        logger.info("Payment created successfully with ID {}", payment.getId());
        return new PaymentResponse(transactionId, "PAYMENT_SUCCESS");

    }

    @Override
    public Payment getPaymentById(Long id) {
        logger.info("Fetching payment with ID {}", id);
        return paymentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Payment not found with ID {}", id);
                    return new PaymentNotFoundException("Payment not found: " + id);
                }
                );
                /*.orElseThrow(() -> new RuntimeException("Payment not found: " + id));*/

    }

    /*@Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }*/

    @Override
    public Page<Payment> getAllPayments(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found: " + id));

        PaymentAudit audit = new PaymentAudit();
        audit.setPaymentId(payment.getId());
        audit.setAction("PAYMENT_DELETED");
        audit.setAuditTime(LocalDateTime.now());

        paymentAuditRepository.save(audit);
        paymentRepository.delete(payment);
    }

    @Override
    public Payment updatePayment(Long id, PaymentRequest request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found: " + id));

        payment.setCustomerId(request.getCustomerId());
        payment.setAccountNumber(request.getAccountNumber());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());

        PaymentAudit audit = new PaymentAudit();
        audit.setPaymentId(payment.getId());
        audit.setAction("PAYMENT_UPDATED");
        audit.setAuditTime(LocalDateTime.now());

        paymentAuditRepository.save(audit);
        return paymentRepository.save(payment);
    }

}
