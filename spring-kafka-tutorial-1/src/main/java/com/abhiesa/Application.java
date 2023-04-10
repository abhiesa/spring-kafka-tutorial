package com.abhiesa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	/*
	@KafkaListener(topics = "foo1", groupId = "bar1-1")
	public void listenGroupFoo(String message) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Received Message in group foo: " + message);
		}
	}

*/
	@KafkaListener(topics = "foo1")
	public void listenWithHeaders(@Payload String message,
					@Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Received Message: " + message + "from partition: " + partition);
		}
	}

}
