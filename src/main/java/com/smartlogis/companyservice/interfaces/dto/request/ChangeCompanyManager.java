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
@Schema(description = "담당 매니저 변경 요청")
public class ChangeCompanyManager {

	@Schema(description = "담당 매니저 id")
	private UUID managerId;
}
