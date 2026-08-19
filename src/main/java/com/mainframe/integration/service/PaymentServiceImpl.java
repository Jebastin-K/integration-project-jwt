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

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return new PaymentResponse("TXN10001", "REJECTED");
        }

        System.out.println("Customer: " + request.getCustomerId());
        System.out.println("Account: " + request.getAccountNumber());
        System.out.println("Amount: " + request.getAmount());
        System.out.println("Currency: " +request.getCurrency());

        String transactionId = "TXN-" + UUID.randomUUID();
        return new PaymentResponse(transactionId, "SUCCESS");
    }
}
