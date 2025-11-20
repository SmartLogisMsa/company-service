package com.smartlogis.companyservice.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartlogis.companyservice.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID>, CompanyRepositoryCustom {

	//업체 등록 전 이미 존재하는지 여부 확인
	boolean existsByNameAndAddress(String name, String address);

}
