package com.mainframe.integration.service;

import com.mainframe.integration.model.PaymentRequest;
import com.mainframe.integration.model.PaymentResponse;
/*import org.springframework.stereotype.Service;*/

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
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
