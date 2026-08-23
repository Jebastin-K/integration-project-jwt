package com.mainframe.integration.service;

import com.mainframe.integration.model.PaymentRequest;
import com.mainframe.integration.model.PaymentResponse;
/*import org.springframework.stereotype.Service;*/
import com.mainframe.integration.model.Payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
    Payment getPaymentById(Long id);
    /*List<Payment> getAllPayments();*/
    Page<Payment> getAllPayments(Pageable pageable);
    void deletePayment(Long id);
    Payment updatePayment(Long id, PaymentRequest request);
}

/*@Service
public class PaymentService {
    public PaymentResponse processPayment(PaymentRequest request) {

        System.out.println("Customer: " + request.getCustomerId());
        System.out.println("Account: " + request.getAccountNumber());
        System.out.println("Amount: " + request.getAmount());
        System.out.println("Currency: " +request.getCurrency());

        return new PaymentResponse("TXN10001", "SUCCESS");
    }
}*/
