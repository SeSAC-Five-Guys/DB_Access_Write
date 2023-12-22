package com.sesac.db_access_write.kafka;


import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sesac.db_access_write.common.dto.ResDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
	private final KafkaTemplate<String, Map<String,String>> kafkaTemplate;

	@Value("${variable.kafka.createMember}")
	private String createMemberTopic;

	@Value("${variable.kafka.updateMember}")
	private String updateMemberTopic;

	@Value("${variable.kafka.deleteMember}")
	private String deleteMemberTopic;

	@Override
	public boolean sendCreateMessage(Map<String, String> registeredMember) {
		try {
			kafkaTemplate.send(createMemberTopic, registeredMember);
			log.info("sendCreateMember Success " + registeredMember.get("nickname"));
			return true;
		}catch (Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
}
