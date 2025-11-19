package com.smartlogis.companyservice.dto.request;

import java.util.UUID;

import com.smartlogis.companyservice.entity.CompanyStatus;
import com.smartlogis.companyservice.entity.CompanyType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "조건별 업체 조회 요청")
public class CompanySearchCondition {

	@Schema(description = "허브 id별 조회", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789", nullable = true)
	private UUID hubId;

	@Schema(description = "상태별 조회", example = "ACTIVE", nullable = true)
	private CompanyStatus status;

	@Schema(description = "타입별 조회", example = "SUPPLIER", nullable = true)
	private CompanyType type;

	@Schema(description = "키워드 기준 조회", example = "서울",  nullable = true)
	private String keyword;
}
