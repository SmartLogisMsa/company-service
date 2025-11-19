package com.smartlogis.companyservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartlogis.companyservice.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

}
