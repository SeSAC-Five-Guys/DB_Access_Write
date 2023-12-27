package com.sesac.db_access_write.member.serviceUtil;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.sesac.db_access_write.common.dto.ErrorStatus;
import com.sesac.db_access_write.common.dto.ResDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class MemberServiceMakeResult {
	public ResDto makeDuplicateEmailResult(){
		return ResDto.builder()
			.success(false)
			.errorStatus(ErrorStatus.DUPLICATE_EMAIL)
			.build();

	}
	public ResDto makeDuplicatedNicknameResult(){
		return ResDto.builder()
			.success(false)
			.errorStatus(ErrorStatus.DUPLICATE_NICKNAME)
			.build();
	}

	public ResDto makeDuplicatedPhoneNumResult(){
		return ResDto.builder()
			.success(false)
			.errorStatus(ErrorStatus.DUPLICATE_PHONENUMBER)
			.build();
	}

	public ResDto makeSendingToKafkaFailedResult(){
		return ResDto.builder()
			.success(false)
			.tmpSvcRes("Sending To Kafka Failed")
			.build();
	}

	// 비지니스 로직 상의 응답에 대응되는 HTTP Status 반환하는 메서드
	public HttpStatus changeStatus(ResDto response) {
		if (!response.isSuccess()){
			return response.getErrorStatus().getHttpStatus();
		}
		return HttpStatus.OK;
	}

	// 비지니스 로직의 Catch 절에서 500 에러에 대한 결과를 응답하는 메서드
	public ResDto makeInternalServerErrorResult(Exception e){
		log.error(e.getMessage());
		log.error(e.getCause());
		return ResDto.builder()
			.success(false)
			.errorStatus(ErrorStatus.INTERNAL_SERVER_ERROR)
			.build();
	}

	// MemberResDto 의 성공결과를 반환하나 data 필드에 반환할 값이 없는 결과를 만드는 메서드
	public ResDto makeSuccessResultNoData(){
		return ResDto.builder()
			.success(true)
			.build();
	}
}
