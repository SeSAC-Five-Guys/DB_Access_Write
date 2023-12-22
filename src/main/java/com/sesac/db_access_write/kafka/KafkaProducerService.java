package com.sesac.db_access_write.kafka;

import java.util.Map;

import org.json.JSONObject;

import com.sesac.db_access_write.common.dto.ResDto;

public interface KafkaProducerService {
	boolean sendCreateMessage(Map<String, String> registeredMember);
}
