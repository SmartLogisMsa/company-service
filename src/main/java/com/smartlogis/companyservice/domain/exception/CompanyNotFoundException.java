package com.smartlogis.companyservice.domain.exception;

import com.smartlogis.common.exception.AbstractException;
import com.smartlogis.common.exception.MessageCode;

public class CompanyNotFoundException extends AbstractException {
	public CompanyNotFoundException(MessageCode messageCode) {
		super(messageCode);
	}

	public CompanyNotFoundException(MessageCode messageCode, Object... messageArguments) {
		super(messageCode, messageArguments);
	}
}
