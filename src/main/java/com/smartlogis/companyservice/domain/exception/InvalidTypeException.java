package com.smartlogis.companyservice.domain.exception;

import com.smartlogis.common.exception.AbstractException;
import com.smartlogis.common.exception.MessageCode;

public class InvalidTypeException extends AbstractException {
	public InvalidTypeException(MessageCode messageCode) {
		super(messageCode);
	}

	public InvalidTypeException(MessageCode messageCode, Object... messageArguments) {
		super(messageCode, messageArguments);
	}
}
