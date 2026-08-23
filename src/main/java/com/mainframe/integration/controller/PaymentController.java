package com.mainframe.integration.controller;

import com.mainframe.integration.model.PaymentRequest;
import com.mainframe.integration.model.PaymentResponse;
import com.mainframe.integration.service.DlqReplayService;
import com.mainframe.integration.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mainframe.integration.model.Payment;

import java.util.List;
import org.springframework.http.HttpStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse createPayment(@Valid @RequestBody PaymentRequest request) {

/*        System.out.println("Customer: " + request.getCustomerId());
        System.out.println("Account: " + request.getAccountNumber());
        System.out.println("Amount: " + request.getAmount());
        System.out.println("Currency: " + request.getCurrency());
        return new PaymentResponse("TXN10001", "SUCCESS"); */

        return paymentService.processPayment(request);


    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
    return paymentService.getPaymentById(id);
    }

    /*@GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }*/
    @GetMapping
    public Page<Payment> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return paymentService.getAllPayments(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentRequest request) {
        return paymentService.updatePayment(id, request);
    }

}