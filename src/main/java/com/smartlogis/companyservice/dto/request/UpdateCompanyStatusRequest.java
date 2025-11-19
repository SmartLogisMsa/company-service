package com.smartlogis.companyservice.dto.request;

import com.smartlogis.companyservice.entity.CompanyStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "업체 상태 변경 요청")
public class UpdateCompanyStatusRequest {

	@Schema(description = "업체 상태")
	private CompanyStatus status;
}
