package com.kafka.poc.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class SolaceProducer {

    private static final Logger logger = LoggerFactory.getLogger(SolaceProducer.class);

    public void sendMessage(StreamBridge streamBridge) {
        for (int i = 0; i < 5; i++) {
            try {
                for (int j = 1; j <= 50; j++) {
                    logger.info("Sending message: shardRandomValue={}, messageId={}", i, j);
                    streamBridge.send("producer-out-0", "shardRandomValue : " + i + ", messageId: " + j);
                }
            } catch (Exception e) {
                logger.error("Error sending message", e);
            }
        }
    }
}
