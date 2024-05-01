package com.kafka.poc.consumer;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public Consumer<String> consumer() {
		return message -> {
			try {
				System.out.println(message);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

}
