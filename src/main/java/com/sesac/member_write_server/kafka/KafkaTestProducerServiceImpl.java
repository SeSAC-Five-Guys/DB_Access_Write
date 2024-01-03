package com.sesac.member_write_server.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KafkaTestProducerServiceImpl {
	private final KafkaTemplate<String, String> kafkaTestTemplate;

	@Value("${variable.kafka.topicTest}")
	private String testTopic;

	public void sendTest(String message) {
		System.out.println(String.format("Produce TestMsg : %s", message));
		kafkaTestTemplate.send(testTopic, message);
	}
}
