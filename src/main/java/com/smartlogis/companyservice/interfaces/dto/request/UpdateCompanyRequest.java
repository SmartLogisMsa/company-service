package com.smartlogis.companyservice.interfaces.dto.request;

import java.util.UUID;

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

	@Schema(description = "업체명", example = "클럽 스파르타")
	private String name;

	@Schema(description = "업체 주소", example = "서울특별시 강남구 도곡로")
	private String address;

	@Schema(description = "허브 id", example = "a1b2c3d4-e5f6-7890-abcd-ef0123456789")
	private UUID hubId;
}
