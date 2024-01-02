package com.sesac.member_write_server.member.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sesac.member_write_server.common.dto.ResDto;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
	@Value("${spring.mail.username}")
	private String setFromEmail;

	private final JavaMailSender javaMailSender;
	@Override
	public ResDto sendMail(String userEmail) throws Exception{
		String setFrom = setFromEmail;
		String htmlContent1 = "<h2>SeSAC_Five_Guys 이메일 인증</h2>";
		String authCode = generateMailAuthCode();
		String htmlContent2 = "<h3>인증 번호는 " + authCode + " 입니다, 회원가입 페이지로 돌아가서 6자리 코드를 입력해 주세요.</h3>";

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper httpMail = new MimeMessageHelper(message, true, "UTF-8");
		httpMail.setFrom(setFrom);
		httpMail.setTo(userEmail);
		httpMail.setSubject("SeSAC_Five_Guys 회원가입 이메일 인증");
		httpMail.setText(htmlContent1 + htmlContent2, true);

		javaMailSender.send(message);
		Map<String, String> responseData = new HashMap<>(Map.ofEntries(
			Map.entry("code", authCode)
		));
		return ResDto.builder()
			.success(true)
			.data(responseData)
			.build();
	}

	@Override
	public String generateMailAuthCode() {
		Random random = new Random();
		return Stream.generate(() -> random.nextInt(2) == 0
				// 소문자 알파벳
				? String.valueOf((char) (random.nextInt(26) + 'a'))
				// 숫자
				: String.valueOf(random.nextInt(10)))
			.limit(6)
			.collect(Collectors.joining());
	}
}
