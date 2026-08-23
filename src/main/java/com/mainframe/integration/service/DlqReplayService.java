package com.mainframe.integration.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class DlqReplayService {
    private final JmsTemplate jmsTemplate;

    public DlqReplayService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void replayMessages() {
        /*String message = (String) jmsTemplate.receiveAndConvert("DLQ");*/
        Object message = jmsTemplate.receiveAndConvert("DLQ");
        System.out.println(message);
        System.out.println(message.getClass());

        if (message != null) {
            jmsTemplate.convertAndSend("PAYMENT.QUEUE", message);
            System.out.println("Message replayed");
        }
        else {
            System.out.println("No messages found in DLQ");
        }
    }
}
