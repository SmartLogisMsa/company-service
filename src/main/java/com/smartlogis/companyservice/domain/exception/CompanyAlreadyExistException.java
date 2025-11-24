package com.smartlogis.companyservice.domain.exception;

import com.smartlogis.common.exception.AbstractException;

public class CompanyAlreadyExistException extends AbstractException {
	public CompanyAlreadyExistException(CompanyCode companyCode) {
		super(companyCode);
	}

	public CompanyAlreadyExistException(CompanyCode companyCode, Object... messageArguments) {
		super(companyCode, messageArguments);
	}
}
