package com.smartlogis.companyservice.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartlogis.companyservice.interfaces.dto.request.CompanySearchCondition;
import com.smartlogis.companyservice.domain.entity.Company;
import com.smartlogis.companyservice.domain.entity.QCompany;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyRepositoryCustomImpl implements CompanyRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Company> searchCompanies(CompanySearchCondition condition, Pageable pageable) {
		QCompany company = QCompany.company;

		// 검색 조건 일부 입력 시 조건 있는 것만 붙여서 쿼리 만들기 -> and로 조건 추가
		BooleanBuilder builder = new BooleanBuilder();

		if(condition.getHubId() != null) {
			builder.and(company.hubId.eq(condition.getHubId()));
		}
		if(condition.getStatus() != null){
			builder.and(company.status.eq(condition.getStatus()));
		}
		if(condition.getType() != null){
			builder.and(company.type.eq(condition.getType()));
		}
		if(condition.getKeyword() != null && !condition.getKeyword().isBlank()){
			builder.and(
				company.name.containsIgnoreCase(condition.getKeyword())
					.or(company.address.containsIgnoreCase(condition.getKeyword()))
			);
		}

		// total count 조회
		long total = queryFactory
			.select(company.count())
			.from(company)
			.where(builder)
			.fetchOne();

		// 페이징된 결과 조회
		List<Company> content = queryFactory
			.selectFrom(company)
			.where(builder)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(company.name.asc())
			.fetch();

		return new PageImpl<>(content, pageable, total);
	}
}
