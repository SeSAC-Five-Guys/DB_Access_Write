package com.sesac.member_write_server.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResDto<T>{
	private boolean success;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T tmpSvcRes;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ErrorStatus errorStatus;
}
