package com.smartlogis.companyservice.interfaces.dto.request;

import java.util.UUID;

import com.smartlogis.companyservice.domain.entity.CompanyType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "업체 생성 요청")
public class CreateCompanyRequest {

	@Schema(description = "업체명")
	private String name;

	@Schema(description = "업체 타입(생산/수령)")
	private CompanyType type;

	@Schema(description = "담당 허브 id")
	private UUID hubId;

	@Schema(description = "업체 주소")
	private String address;

	@Schema(description = "담당 매니저 id")
	private UUID managerId;
}
