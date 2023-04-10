package com.abhiesa;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
class ApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationTests.class);

	private static final String MESSAGE = "hello world";
	@Autowired
	private KafkaTemplate<String, String> template;

	@Value(value = "${message.topic.name}")
	private String topicName;

	@Test
	void sendToKafka() {
		final String message = MESSAGE+ LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
		template.send(topicName, message);
	}

	@Test
	void sendToKafkaWithReturn() {
		final String message = MESSAGE+ LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		final CompletableFuture<SendResult<String, String>> future
						= template.send(topicName,message);
		future.whenComplete((result, ex) ->{
			if (null == ex && LOG.isInfoEnabled()) {
				LOG.info("Sent message=[" + message +
								"] with offset=[" + result.getRecordMetadata().offset() + "]");
			} else if (LOG.isErrorEnabled()){
				LOG.error("Unable to send message=[" +
								message + "] due to : " + ex.getMessage(), ex);
			}
		});
	}



}
