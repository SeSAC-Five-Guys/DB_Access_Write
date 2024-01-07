package com.sesac.member_write_server.member.service;

import com.sesac.member_write_server.common.dto.ResDto;
import com.sesac.member_write_server.member.dto.MemberModifyInfo;
import com.sesac.member_write_server.member.dto.MemberSignUpInfo;

public interface MemberService {
	ResDto isDuplicatedEmail(String email);
	ResDto isDuplicatedPhoneNumber(String phoneNumber);
	ResDto isDuplicatedNickname(String nickname);
	ResDto createMember(MemberSignUpInfo signUpInfo);
	ResDto isDuplicatedNicknameInModify(String currentNick, String changeNick);
	ResDto isDuplicatedPhoneNumInModify(String currentPhoneNum, String changePhoneNum);
	ResDto modifyMember(String email, MemberModifyInfo memberModifyInfo);
	ResDto deleteMember(String email);
}
