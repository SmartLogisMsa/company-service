package com.smartlogis.companyservice.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "업체 타입")
public enum CompanyType {
	SUPPLIER,
	RECEIVER
}
