package com.smartlogis.companyservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlogis.companyservice.dto.request.CreateCompanyRequest;
import com.smartlogis.companyservice.dto.request.UpdateCompanyStatusRequest;
import com.smartlogis.companyservice.dto.response.CompanyResponse;
import com.smartlogis.companyservice.entity.Company;
import com.smartlogis.companyservice.entity.CompanyStatus;
import com.smartlogis.companyservice.exception.CompanyCode;
import com.smartlogis.companyservice.exception.CompanyException;
import com.smartlogis.companyservice.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

	private final CompanyRepository companyRepository;

	//1. 업체 생성
	@Transactional
	public CompanyResponse createCompany(CreateCompanyRequest request) {
		Company newCompany = Company.create(request);

		Company savedCompany = companyRepository.save(newCompany);

		return new CompanyResponse(
			savedCompany.getId(),
			savedCompany.getName(),
			savedCompany.getType(),
			savedCompany.getHubId(),
			savedCompany.getAddress(),
			savedCompany.getStatus(),
			savedCompany.getManagerId()
		);
	}

	//2. 업체 상세 조회

	//3. 업체 목록 조회

	//4. 업체 정보 수정


	//5. 업체 상태 수정
	@Transactional
	public CompanyResponse updateCompanyStatus(UUID id, UpdateCompanyStatusRequest request) {

		// 업체 존재 여부 체크
		Company company = companyRepository.findById(id)
			.orElseThrow(() -> new CompanyException(CompanyCode.CompanyNotFound));

		company.updateStatus(request.getStatus());

		return CompanyResponse.of(company);
	}

	//6. 업체 담당자 변경

	//7. 업체 삭제

}
