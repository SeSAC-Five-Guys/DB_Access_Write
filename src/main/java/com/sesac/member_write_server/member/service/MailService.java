package com.sesac.member_write_server.member.service;

import com.sesac.member_write_server.common.dto.ResDto;

public interface MailService {
	ResDto sendMail(String userEmail) throws Exception;
	String generateMailAuthCode();
}
