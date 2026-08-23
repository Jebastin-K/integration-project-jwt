package com.mainframe.integration.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import com.mainframe.integration.model.PaymentEvent;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class PaymentProducer {
    private final JmsTemplate jmsTemplate;
    private final Counter producedCounter;

    public PaymentProducer(JmsTemplate jmsTemplate, MeterRegistry meterRegistry) {

        this.jmsTemplate = jmsTemplate;
        this.producedCounter = Counter.builder("payment_messages_produced_total")
                .description("Total Payment Messages sent")
                .register(meterRegistry);
    }

    public void sendMessage(PaymentEvent event) {
        System.out.println("About to send event");
        jmsTemplate.convertAndSend("PAYMENT.QUEUE", event);
        producedCounter.increment();
        System.out.println("Event sent successfully");
    }
    /*public void sendMessage(String message) {
        jmsTemplate.convertAndSend("PAYMENT.QUEUE", message);
    }*/
}
