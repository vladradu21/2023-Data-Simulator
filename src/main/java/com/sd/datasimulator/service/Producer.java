package com.sd.datasimulator.service;

import com.sd.datasimulator.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

@Component
public class Producer {
    private final KafkaTemplate<String, MessageDTO> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, MessageDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void produce() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send("monitoring", new MessageDTO("vladradu", "bec", LocalDateTime.now().minusHours(1), (Math.round((Math.random() * 9_00) + 100) / 100.0) ));
            kafkaTemplate.send("monitoring", new MessageDTO("dumitruradu", "altbec", LocalDateTime.now().minusHours(1), (Math.round((Math.random() * 9_00) + 100) / 100.0) ));
            sleep(5000);
        }
    }
}