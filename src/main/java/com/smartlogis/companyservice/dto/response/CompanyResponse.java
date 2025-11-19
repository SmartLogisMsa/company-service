package com.smartlogis.companyservice.dto.response;

import java.util.UUID;

import com.smartlogis.companyservice.entity.Company;
import com.smartlogis.companyservice.entity.CompanyStatus;
import com.smartlogis.companyservice.entity.CompanyType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "업체 생성 요청 응답")
public class CompanyResponse {

	@Schema(description = "업체 id")
	private UUID id;

	@Schema(description = "업체명")
	private String name;

	@Schema(description = "업체 타입(생산/수령)")
	private CompanyType type;

	@Schema(description = "담당 허브 id")
	private UUID hubId;

	@Schema(description = "업체 주소")
	private String address;

	@Schema(description = "업체 상태")
	private CompanyStatus status;

	@Schema(description = "담당 매니저 id")
	private UUID managerId;

	public static CompanyResponse of(Company company) {
		return CompanyResponse.builder()
			.id(company.getId())
			.name(company.getName())
			.type(company.getType())
			.hubId(company.getHubId())
			.address(company.getAddress())
			.status(company.getStatus())
			.managerId(company.getManagerId())
			.build();
	}
}
