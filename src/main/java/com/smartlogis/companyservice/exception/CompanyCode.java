package com.smartlogis.companyservice.exception;

import org.springframework.http.HttpStatus;

import com.smartlogis.common.exception.MessageCode;

public enum CompanyCode implements MessageCode {
	INVALID_NAME("COMPANY.INVALID_NAME", HttpStatus.BAD_REQUEST),
	INVALID_STATUS("COMPANY.INVALID_STATUS", HttpStatus.BAD_REQUEST),
	INVALID_ADDRESS("COMPANY.INVALID_ADDRESS", HttpStatus.BAD_REQUEST),
	INVALID_TYPE("COMPANY.INVALID_TYPE", HttpStatus.BAD_REQUEST),
	INVALID_HUBID("COMPANY.INVALID_HUBID", HttpStatus.BAD_REQUEST),
	INVALID_MANAGERID("COMPANY.INVALID_MANAGERID", HttpStatus.BAD_REQUEST),
	CompanyNotFound("COMPANY.NOT_FOUND", HttpStatus.NOT_FOUND),
	;

	private final String code;
	private final HttpStatus status;

	CompanyCode(String code, HttpStatus status) {
		this.code = code;
		this.status = status;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}
}
