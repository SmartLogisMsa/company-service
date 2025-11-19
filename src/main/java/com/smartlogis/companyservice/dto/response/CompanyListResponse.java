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
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "업체 목록 조회 응답")
public class CompanyListResponse {

	@Schema(description = "업체 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789", nullable = false)
	private UUID id;

	@Schema(description = "업체명", example = "스파르타서울", nullable = false)
	private String name;

	@Schema(description = "업체 타입", example = "SUPPLIER", nullable = false)
	private CompanyType type;

	@Schema(description = "업체 상태", example = "ACTIVE", nullable = false)
	private CompanyStatus status;

	//dto 변환 메서드
	public static CompanyListResponse from(Company company){
		return CompanyListResponse.builder()
			.id(company.getId())
			.name(company.getName())
			.type(company.getType())
			.status(company.getStatus())
			.build();
	}
}
