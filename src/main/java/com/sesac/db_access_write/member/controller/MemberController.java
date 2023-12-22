package com.sesac.db_access_write.member.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sesac.db_access_write.common.dto.ResDto;
import com.sesac.db_access_write.member.service.MemberService;
import com.sesac.db_access_write.member.serviceUtil.MemberServiceMakeResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/members")
public class MemberController {
	private final MemberService memberService;
	private final MemberServiceMakeResult makeResult;
	@PostMapping("/member")
	public ResponseEntity<ResDto> MemberCreateAndSendMQ(
		@Valid @RequestBody Map<String, String> signUpInfo
	){
		ResDto response = memberService.signup(signUpInfo);
		HttpStatus status = makeResult.changeStatus(response);
		return new ResponseEntity<>(response, status);
	}
}
