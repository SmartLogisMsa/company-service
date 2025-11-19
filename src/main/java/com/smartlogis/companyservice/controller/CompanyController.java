package com.smartlogis.companyservice.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartlogis.common.presentation.ApiResponse;
import com.smartlogis.companyservice.dto.request.CreateCompanyRequest;
import com.smartlogis.companyservice.dto.request.UpdateCompanyStatusRequest;
import com.smartlogis.companyservice.dto.response.CompanyResponse;
import com.smartlogis.companyservice.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/companies")
@RequiredArgsConstructor
@Tag(name = "업체", description = "업체 관련 API")
public class CompanyController {

	private final CompanyService companyService;

	//1. 업체 생성
	@PostMapping("")
	@Operation(summary = "새 업체 생성", description = "새로운 업체를 생성합니다.")
	// @PreAuthorize("hasRole('MASTER') or hasRole('HUB_MANAGER')")
	public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(
		@RequestBody @Valid CreateCompanyRequest request
	){
		CompanyResponse response = companyService.createCompany(request);
		return ResponseEntity.ok(ApiResponse.successWithDataOnly(response));
	}

	//2. 업체 상세 조회

	//3. 업체 목록 조회

	//4. 업체 정보 수정

	//5. 업체 상태 수정
	@PatchMapping("/{id}/status")
	@Operation(summary = "업체 상태 수정", description = "업체 상태를 ACTIVE/INACTIVE로 수정합니다.")
	// @PreAuthorize("hasRole('MASTER') or hasRole('HUB_MANAGER') or hasRole('COMPANY_MANAGER')")
	public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(
		@PathVariable UUID id,
		@RequestBody @Valid UpdateCompanyStatusRequest request
	){
		CompanyResponse response = companyService.updateCompanyStatus(id, request);
		return ResponseEntity.ok(ApiResponse.successWithDataOnly(response));
	}

	//6. 업체 담당자 변경

	//7. 업체 삭제
}
