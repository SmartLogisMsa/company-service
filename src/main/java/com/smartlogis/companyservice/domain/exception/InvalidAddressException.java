package com.smartlogis.companyservice.domain.exception;

import com.smartlogis.common.exception.AbstractException;
import com.smartlogis.common.exception.MessageCode;

public class InvalidAddressException extends AbstractException {
	public InvalidAddressException(MessageCode messageCode) {
		super(messageCode);
	}

	public InvalidAddressException(MessageCode messageCode, Object... messageArguments) {
		super(messageCode, messageArguments);
	}
}
