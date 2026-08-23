package com.mainframe.integration.messaging;

import com.mainframe.integration.model.PaymentEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class PaymentConsumer {
    private final Counter consumedCounter;

    public PaymentConsumer(MeterRegistry meterRegistry) {
        this.consumedCounter = Counter.builder("payment_messages_consumed_total")
                .description("Total payment messages consumed")
                .register(meterRegistry);
    }

    @JmsListener(destination = "PAYMENT.QUEUE")
    public void receiveMessage(PaymentEvent event) {
        System.out.println("Payment Id : " + event.getPaymentId());
        System.out.println("Customer Id : " + event.getCustomerId());
        System.out.println("Amount : " + event.getAmount());
        System.out.println("Status : " + event.getStatus());
        consumedCounter.increment();

       /*throw new RuntimeException("Testing DLQ");*/
    }
  /*  public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
    }*/
}
