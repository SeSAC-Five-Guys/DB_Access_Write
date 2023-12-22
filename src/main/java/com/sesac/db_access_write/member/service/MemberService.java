package com.sesac.db_access_write.member.service;

import java.util.Map;

import org.json.JSONObject;

import com.sesac.db_access_write.common.dto.ResDto;

public interface MemberService {
	ResDto signup(Map<String, String> signUpInfo);
}
