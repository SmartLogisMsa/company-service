package com.smartlogis.companyservice.exception;

import com.smartlogis.common.exception.AbstractException;

public class CompanyException extends AbstractException {
	public CompanyException(CompanyCode code, Object... args) {
		super(code, args);
	}

}
