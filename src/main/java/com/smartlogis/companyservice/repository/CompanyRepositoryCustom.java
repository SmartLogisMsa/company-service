package com.smartlogis.companyservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartlogis.companyservice.dto.request.CompanySearchCondition;
import com.smartlogis.companyservice.dto.response.CompanyListResponse;
import com.smartlogis.companyservice.entity.Company;

public interface CompanyRepositoryCustom {

	// 통합 검색(hub id, 업체 상태, 업체 타입, 키워드, 페이징)
	Page<Company> searchCompanies(CompanySearchCondition condition, Pageable pageable);
}
