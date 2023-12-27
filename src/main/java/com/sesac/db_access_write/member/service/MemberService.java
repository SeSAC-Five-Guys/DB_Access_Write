package com.sesac.db_access_write.member.service;

import java.util.Map;

import org.json.JSONObject;

import com.sesac.db_access_write.common.dto.ResDto;
import com.sesac.db_access_write.member.dto.MemberModifyInfo;
import com.sesac.db_access_write.member.dto.MemberSignUpInfo;

public interface MemberService {
	ResDto isDuplicatedEmail(String email);
	ResDto isDuplicatedPhoneNumber(String phoneNumber);
	ResDto isDuplicatedNickname(String nickname);
	ResDto signup(MemberSignUpInfo signUpInfo);

	ResDto modifyMember(String email, MemberModifyInfo memberModifyInfo);
}
