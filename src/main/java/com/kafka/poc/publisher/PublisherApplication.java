package com.kafka.poc.publisher;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PublisherApplication {
	private static final Logger logger = LoggerFactory.getLogger(PublisherApplication.class);
	@Autowired
	private StreamBridge streamBridge;

	@Autowired
	private SolaceProducer solaceProducer;

	private boolean flag = false; // Initialize flag as false

	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}

	@Bean
	public Supplier<String> producer(StreamBridge streamBridge) {
		return () -> {
			while (true) {
				try {
					if (flag) {
						solaceProducer.sendMessage(streamBridge);
					} else {
						// Pause message production when flag is false
						Thread.sleep(1000); // Adjust the sleep duration as needed
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // Restore interrupted status
					// Handle the InterruptedException if needed
					logger.error("Thread interrupted while sleeping", e);
				} catch (Exception e) {
					logger.error("Error in producer", e);
				}
			}
		};
	}

	@RestController
	public class MessageController {

		@GetMapping("/start")
		public String startMessage() {
			flag = true; // Set flag to true to start message production
			return "Message production started.";
		}

		@GetMapping("/stop")
		public String stopMessage() {
			flag = false; // Set flag to false to stop message production
			return "Message production stopped.";
		}

		@GetMapping("/sayhi")
		public String sayHi() {
			return "Hello Everyone!";
		}
	}
}
