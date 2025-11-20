package com.smartlogis.companyservice.domain.exception;

import com.smartlogis.common.exception.AbstractException;

public class InvalidNameException extends AbstractException {
	public InvalidNameException(CompanyCode companyCode) {
		super(companyCode);
	}

	public InvalidNameException(CompanyCode companyCode, Object... messageArguments) {
		super(companyCode, messageArguments);
	}
}
