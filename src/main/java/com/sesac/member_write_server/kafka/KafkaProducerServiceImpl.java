package com.sesac.member_write_server.kafka;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sesac.member_write_server.member.entity.Member;

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
	public boolean sendCreateMemberMsg(Map<String, String> registeredMember) {
		try {
			kafkaTemplate.send(createMemberTopic, registeredMember);
			log.info("sendCreateMember Success " + registeredMember.get("nickname"));
			return true;
		}catch (Exception e){
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean sendUpdateMemberMsg(Map<String, String> modifiedMember) {
		try {
			kafkaTemplate.send(updateMemberTopic, modifiedMember);
			log.info("sendUpdateMember Success " + modifiedMember.get("nickname"));
			return true;
		} catch (Exception e){
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean sendDeletedMemberMsg(Map<String, String> deletedMember) {
		try {
			kafkaTemplate.send(deleteMemberTopic, deletedMember);
			log.info("send DeleteMember Success " + deletedMember.get("nickname"));
			return true;
		} catch (Exception e){
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public Map<String, String> getMemberToKafkaCreateMemberMap(Member savedMember) {
		Map<String, String> registeredMember = new HashMap<>();

		registeredMember.put("memberId", savedMember.getMemberId().toString());
		registeredMember.put("createdAt", savedMember.getCreatedAt().toString());
		registeredMember.put("updatedAt", savedMember.getUpdatedAt().toString());
		registeredMember.put("deletedAt", "");
		registeredMember.put("email", savedMember.getEmail());
		registeredMember.put("phoneNumber", savedMember.getPhoneNumber());
		registeredMember.put("nickname", savedMember.getNickname());
		registeredMember.put("memberRole", savedMember.getMemberRole().toString());
		registeredMember.put("password", savedMember.getPassword());

		return registeredMember;
	}

	@Override
	public Map<String, String> getMemberToKafkaUpdateMemberMap(Member savedMember) {
		Map<String, String> modifiedMember = new HashMap<>();

		modifiedMember.put("email", savedMember.getEmail());
		modifiedMember.put("phoneNumber", savedMember.getPhoneNumber());
		modifiedMember.put("nickname", savedMember.getNickname());

		return modifiedMember;
	}

	@Override
	public Map<String, String> getMemberToKafkaDeleteMemberMap(Member savedMember) {
		Map<String, String> deletedMember = new HashMap<>();

		deletedMember.put("email", savedMember.getEmail());
		deletedMember.put("nickname", savedMember.getNickname());
		deletedMember.put("deletedAt", savedMember.getDeletedAt().toString());

		return deletedMember;
	}

}
