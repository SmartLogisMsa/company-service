package com.smartlogis.companyservice.interfaces.dto.response;

import java.util.UUID;

import com.smartlogis.companyservice.domain.entity.Company;
import com.smartlogis.companyservice.domain.entity.CompanyStatus;
import com.smartlogis.companyservice.domain.entity.CompanyType;

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

	@Schema(description = "업체 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789")
	private UUID id;

	@Schema(description = "업체명", example = "스파르타서울")
	private String name;

	@Schema(description = "업체 타입(생산/수령)", example = "SUPPLIER")
	private CompanyType type;

	@Schema(description = "담당 허브 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789")
	private UUID hubId;

	@Schema(description = "업체 주소", example = "서울특별시 강남구 도곡로")
	private String address;

	@Schema(description = "업체 상태", example = "ACTIVE")
	private CompanyStatus status;

	@Schema(description = "담당 매니저 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789")
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
