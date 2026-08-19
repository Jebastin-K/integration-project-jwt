package com.mainframe.integration.controller;

import com.mainframe.integration.model.PaymentRequest;
import com.mainframe.integration.model.PaymentResponse;
import com.mainframe.integration.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse createPayment(@RequestBody PaymentRequest request) {

/*        System.out.println("Customer: " + request.getCustomerId());
        System.out.println("Account: " + request.getAccountNumber());
        System.out.println("Amount: " + request.getAmount());
        System.out.println("Currency: " + request.getCurrency());
        return new PaymentResponse("TXN10001", "SUCCESS"); */

        return paymentService.processPayment(request);


    }
}

