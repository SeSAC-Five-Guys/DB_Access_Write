package com.sesac.db_access_write.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProducerServiceImpl implements ProducerService{
	@Value("${variable.kafka.topicTest}")
	private String testTopic;

	private final KafkaTemplate<String, String> kafkaTemplate;
	@Override
	public void sendTest(String message) {
		System.out.println(String.format("Produce TestMsg : %s", message));
		kafkaTemplate.send(testTopic, message);
	}
}
