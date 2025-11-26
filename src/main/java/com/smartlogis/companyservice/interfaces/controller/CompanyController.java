package com.smartlogis.companyservice.interfaces.controller;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartlogis.common.presentation.ApiResponse;
import com.smartlogis.common.presentation.dto.PageResponse;
import com.smartlogis.companyservice.interfaces.dto.request.ChangeCompanyManager;
import com.smartlogis.companyservice.interfaces.dto.request.CompanySearchCondition;
import com.smartlogis.companyservice.interfaces.dto.request.CreateCompanyRequest;
import com.smartlogis.companyservice.interfaces.dto.request.UpdateCompanyRequest;
import com.smartlogis.companyservice.interfaces.dto.request.UpdateCompanyStatusRequest;
import com.smartlogis.companyservice.interfaces.dto.response.CompanyResponse;
import com.smartlogis.companyservice.application.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
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
	@GetMapping("/{id}")
	@Operation(summary = "업체 상세 조회", description = "하나의 업체를 상세하게 조회합니다.")
	public ResponseEntity<ApiResponse<CompanyResponse>> getCompany(
		@PathVariable UUID id
	){
		CompanyResponse response = companyService.getCompany(id);
		return ResponseEntity.ok(ApiResponse.successWithDataOnly(response));
	}

	//3. 업체 목록 조회
	@GetMapping("")
	@Operation(summary = "조건별 업체 목록 조회", description = "조건에 따라 해당하는 모든 업체의 목록을 조회합니다.")
	public ResponseEntity<ApiResponse<PageResponse<CompanyResponse>>> getAllCompanies(
		@ModelAttribute CompanySearchCondition condition,
		Pageable pageable
	){
		PageResponse<CompanyResponse> response = companyService.getCompanies(condition, pageable);
		return  ResponseEntity.ok(ApiResponse.successWithDataOnly(response));
	}

	//4. 업체 정보 수정
	@PutMapping("/{id}")
	@Operation(summary = "업체 정보 수정", description = "업체 정보를 수정합니다.")
	// @PreAuthorize("hasRole('MASTER') or hasRole('HUB_MANAGER') or hasRole('COMPANY_MANAGER')")
	public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(
		@PathVariable UUID id,
		@RequestBody @Valid UpdateCompanyRequest request
	){
		CompanyResponse response = companyService.updateCompany(id, request);
		return ResponseEntity.ok(ApiResponse.successWithDataOnly(response));
	}

	//5. 업체 상태 수정
	@PatchMapping("/{id}/status")
	@Operation(summary = "업체 상태 수정", description = "업체 상태를 ACTIVE/INACTIVE로 수정합니다.")
	// @PreAuthorize("hasRole('MASTER') or hasRole('HUB_MANAGER') or hasRole('COMPANY_MANAGER')")
	public ResponseEntity<ApiResponse<CompanyResponse>> updateStatus(
		@PathVariable UUID id,
		@RequestBody @Valid UpdateCompanyStatusRequest request
	){
		CompanyResponse response = companyService.updateCompanyStatus(id, request);
		return ResponseEntity.ok(ApiResponse.successWithDataOnly(response));
	}

	//6. 업체 담당자 변경
	@PatchMapping("/{id}/manager")
	@Operation(summary = "업체 담당 매니저 변경", description = "업체 매니저를 변경합니다.")
	// @PreAuthorize("hasRole('MASTER')")
	public ResponseEntity<ApiResponse<CompanyResponse>> updateManager(
		@PathVariable UUID id,
		@RequestBody @Valid ChangeCompanyManager request
	){
		CompanyResponse response = companyService.changeCompanyManager(id, request);
		return ResponseEntity.ok(ApiResponse.successWithDataOnly(response));
	}

	//7. 업체 삭제
	@DeleteMapping("/{id}")
	@Operation(summary = "업체 삭제", description = "하나의 업체를 삭제합니다.")
	// @PreAuthorize("hasRole('MASTER') or hasRole('HUB_MANAGER')")
	public ResponseEntity<ApiResponse<Void>> deleteCompany(
		@PathVariable UUID id
	){
		companyService.deleteCompany(id);
		return ResponseEntity.ok(ApiResponse.success());
	}
}
