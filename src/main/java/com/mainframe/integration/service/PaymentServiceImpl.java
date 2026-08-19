package com.mainframe.integration.service;

import com.mainframe.integration.model.PaymentRequest;
import com.mainframe.integration.model.PaymentResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {

        String transactionId = "TXN-" + UUID.randomUUID();

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return new PaymentResponse("TXN10001", "REJECTED");
        }

        if (request.getAmount().compareTo(new BigDecimal("100000")) > 0 ) {
            return new PaymentResponse(transactionId,"LIMIT_EXCEEDED");
        }

        System.out.println("Customer: " + request.getCustomerId());
        System.out.println("Account: " + request.getAccountNumber());
        System.out.println("Amount: " + request.getAmount());
        System.out.println("Currency: " +request.getCurrency());

        return new PaymentResponse(transactionId, "PAYMENT_SUCCESS");
    }
}
