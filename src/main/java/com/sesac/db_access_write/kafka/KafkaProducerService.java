package com.sesac.db_access_write.kafka;

import java.util.Map;

import org.json.JSONObject;

import com.sesac.db_access_write.common.dto.ResDto;
import com.sesac.db_access_write.member.entity.Member;

public interface KafkaProducerService {
	// Read Server 로 가입된 회원의 정보를 보내는 메서드
	boolean sendCreateMemberMsg(Map<String, String> registeredMember);

	// Read Server 로 수정된 회원의 정보를 보내는 메서드
	boolean sendUpdateMemberMsg(Map<String, String> modifiedMember);

	// Read Server 로 수정된 회원의 정보를 보내는 메서드
	boolean sendDeletedMemberMsg(Map<String, String> deletedMember);

	// 가입된 회원의 정보를 Kafka 로 보낼 데이터로 변경하는 메서드
	Map<String, String> getMemberToKafkaCreateMemberMap(Member savedMember);

	// 변경된 회원의 정보를 Kafka 로 보낼 데이터로 변경하는 메서드
	Map<String, String> getMemberToKafkaUpdateMemberMap(Member savedMember);
}
