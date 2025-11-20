package com.smartlogis.companyservice.interfaces.dto.response;

import java.time.LocalDateTime;
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
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "업체 상세 조회 응답")
public class CompanyDetailResponse {

	@Schema(description = "업체 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789", nullable = false)
	private UUID id;

	@Schema(description = "업체명", example = "스파르타서울", nullable = false)
	private String name;

	@Schema(description = "업체 타입", example = "SUPPLIER", nullable = false)
	private CompanyType type;

	@Schema(description = "소속 허브 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789", nullable = false)
	private UUID hubId;

	@Schema(description = "업체 주소", example = "서울특별시 강남구 도곡로", nullable = false)
	private String address;

	@Schema(description = "업체 상태", example = "ACTIVE", nullable = false)
	private CompanyStatus status;

	@Schema(description = "담당 매니저 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789", nullable = false)
	private UUID managerId;

	@Schema(description = "생성 일시", example = "2025-11-19T09:30:00", nullable = false)
	private LocalDateTime createdAt;

	@Schema(description = "생성자", example = "admin", nullable = false)
	private String createdBy;

	@Schema(description = "수정 일시", example = "2025-11-19T10:00:00", nullable = false)
	private LocalDateTime updatedAt;

	@Schema(description = "수정자", example = "manager", nullable = false)
	private String updatedBy;

	@Schema(description = "삭제 일시", example = "2025-11-19T11:00:00", nullable = true)
	private LocalDateTime deletedAt;

	@Schema(description = "삭제자", example = "admin", nullable = true)
	private String deletedBy;

	//dto 변환 메서드
	public static CompanyDetailResponse from(Company company){
		return CompanyDetailResponse.builder()
			.id(company.getId())
			.name(company.getName())
			.type(company.getType())
			.hubId(company.getHubId())
			.address(company.getAddress())
			.status(company.getStatus())
			.managerId(company.getManagerId())
			.createdAt(company.getCreatedAt())
			.createdBy(company.getCreatedBy())
			.updatedAt(company.getUpdatedAt())
			.updatedBy(company.getUpdatedBy())
			.deletedAt(company.getDeletedAt())
			.deletedBy(company.getDeletedBy())
			.build();
	}
}
