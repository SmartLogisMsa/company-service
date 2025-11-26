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

	@Schema(description = "업체명", example = "스파르타 클럽")
	private String name;

	@Schema(description = "업체 타입(생산/수령)", example = "SUPPLIER")
	private CompanyType type;

	@Schema(description = "담당 허브 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789")
	private UUID hubId;

	@Schema(description = "업체 주소", example = "서울특별시 강남구 도곡로")
	private String address;

	@Schema(description = "담당 매니저 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789")
	private UUID managerId;
}
