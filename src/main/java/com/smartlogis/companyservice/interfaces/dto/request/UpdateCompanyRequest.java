package com.smartlogis.companyservice.interfaces.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "업체 정보 수정 요청")
public class UpdateCompanyRequest {

	@Schema(description = "업체명")
	private String name;

	@Schema(description = "업체 주소")
	private String address;
}
