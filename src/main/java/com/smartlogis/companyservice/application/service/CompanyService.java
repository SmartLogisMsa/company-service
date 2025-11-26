package com.smartlogis.companyservice.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlogis.common.presentation.dto.PageResponse;
import com.smartlogis.companyservice.infrastructure.event.publisher.CompanyEventPublisher;
import com.smartlogis.companyservice.interfaces.dto.event.CompanyHubChangeEvent;
import com.smartlogis.companyservice.interfaces.dto.event.CompanyInactivatedEvent;
import com.smartlogis.companyservice.interfaces.dto.event.CompanyOrderCreatedEvent;
import com.smartlogis.companyservice.interfaces.dto.event.CompanyStatusChangedEvent;
import com.smartlogis.companyservice.interfaces.dto.event.HubDeletedMessage;
import com.smartlogis.companyservice.interfaces.dto.event.LowStockEvent;
import com.smartlogis.companyservice.interfaces.dto.event.OrderCreatedEvent;
import com.smartlogis.companyservice.interfaces.dto.event.StockReplenishedEvent;
import com.smartlogis.companyservice.interfaces.dto.request.ChangeCompanyManager;
import com.smartlogis.companyservice.interfaces.dto.request.CompanySearchCondition;
import com.smartlogis.companyservice.interfaces.dto.request.CreateCompanyRequest;
import com.smartlogis.companyservice.interfaces.dto.request.UpdateCompanyRequest;
import com.smartlogis.companyservice.interfaces.dto.request.UpdateCompanyStatusRequest;
import com.smartlogis.companyservice.interfaces.dto.response.CompanyResponse;
import com.smartlogis.companyservice.domain.entity.Company;
import com.smartlogis.companyservice.domain.exception.CompanyAlreadyExistException;
import com.smartlogis.companyservice.domain.exception.CompanyCode;
import com.smartlogis.companyservice.domain.exception.CompanyNotFoundException;
import com.smartlogis.companyservice.domain.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

	private final CompanyRepository companyRepository;
	private final CompanyEventPublisher companyEventPublisher;

	//1. 업체 생성
	@Transactional
	public CompanyResponse createCompany(CreateCompanyRequest request) {

		//이름과 주소로 중복 등록 여부 확인
		boolean exists = companyRepository.existsByNameAndAddress(request.getName(), request.getAddress());

		if (exists) {
			throw new CompanyAlreadyExistException(CompanyCode.CompanyAlreadyExist);
		}

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
	@Transactional(readOnly = true)
	public CompanyResponse getCompany(UUID id) {

		// 업체 존재 여부 체크
		Company company = companyRepository.findById(id)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyCode.CompanyNotFound));

		return CompanyResponse.of(company);
	}

	//3. 업체 목록 조회
	@Transactional(readOnly = true)
	public PageResponse<CompanyResponse> getCompanies(CompanySearchCondition condition, Pageable pageable) {

		Page<Company> companies = companyRepository.searchCompanies(condition, pageable);

		return PageResponse.from(
			companies.map(CompanyResponse::of)
		);
	}

	//4. 업체 정보 수정
	@Transactional
	public CompanyResponse updateCompany(UUID id, UpdateCompanyRequest request) {

		// 업체 존재 여부 체크
		Company company = companyRepository.findById(id)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyCode.CompanyNotFound));

		UUID previousHubId = company.getHubId();

		if(request.getName() != null){
			company.changeName(request.getName());
		}

		if(request.getAddress() != null){
			company.changeAddress(request.getAddress());
		}

		if(request.getHubId() != null){
			company.changeHub(request.getHubId());
		}

		UUID newHubId = request.getHubId();

		if(!previousHubId.equals(newHubId)){
			//허브 변경 이벤트 발행
			CompanyHubChangeEvent event = new CompanyHubChangeEvent(
				company.getId(),
				company.getHubId()
			);
			companyEventPublisher.publishChangedHubId(event);
		}

		return CompanyResponse.of(company);
	}


	//5. 업체 상태 수정
	@Transactional
	public CompanyResponse updateCompanyStatus(UUID id, UpdateCompanyStatusRequest request) {

		// 업체 존재 여부 체크
		Company company = companyRepository.findById(id)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyCode.CompanyNotFound));

		company.updateStatus(request.getStatus());

		//업체 상태에 따라 상품도 상태 바뀌도록 이벤트 발행
		CompanyStatusChangedEvent event = new CompanyStatusChangedEvent(
			company.getId(), request.getStatus().name()
		);

		companyEventPublisher.publishStatusChanged(event);

		return CompanyResponse.of(company);
	}

	//6. 업체 담당자 변경
	@Transactional
	public CompanyResponse changeCompanyManager(UUID id, ChangeCompanyManager request){

		// 업체 존재 여부 체크
		Company company = companyRepository.findById(id)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyCode.CompanyNotFound));

		company.changeManagerId(request.getManagerId());

		return CompanyResponse.of(company);
	}


	//7. 업체 삭제
	@Transactional
	public void deleteCompany(UUID id) {
		// 업체 존재 여부 체크
		Company company = companyRepository.findById(id)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyCode.CompanyNotFound));

		company.delete();

		//삭제된 업체의 상품도 비활성화하는 이벤트
		CompanyInactivatedEvent message = new CompanyInactivatedEvent(company.getId());

		companyEventPublisher.publishCompanyInactivated(message);
	}

	//8. 이벤트 받아서 도착 허브 id 추가
	@Transactional
	public void handleOrderCreatedEvent(OrderCreatedEvent event) {

		//수령업체 id로 업체 조회
		Company company = companyRepository.findById(event.getReceiptCompanyId())
			.orElseThrow(() -> new CompanyNotFoundException(CompanyCode.CompanyNotFound));

		List<CompanyOrderCreatedEvent.OrderItemDetail> orderItems = event.getOrderItems()
			.stream()
			.map(item -> CompanyOrderCreatedEvent.OrderItemDetail.builder()
				.productId(item.getProductId())
				.quantity(item.getQuantity())
				.build())
			.toList();

		CompanyOrderCreatedEvent companyEvent = CompanyOrderCreatedEvent.builder()
			.orderId(event.getOrderId())
			.orderItems(orderItems)
			.address(event.getAddress())
			.receiptUserId(event.getReceiptUserId())
			.destinationHubId(company.getHubId())
			.build();

		companyEventPublisher.publishToProduct(companyEvent);
	}

	//9. 허브 삭제 이벤트 처리
	@Transactional
	public void handleHubDeleted(HubDeletedMessage event) {

		UUID hubId = event.hubId();

		//해당 허브 id 가진 업체 조회해서 모조리 inactive로 바꾸기
		List<Company> companies = companyRepository.findAllByHubId(hubId);

		//업체 비활성화
		companies.forEach(Company::inactivate);

		//상품도 비활성화하는 이벤트 발행
		companies.forEach(company -> {
			CompanyInactivatedEvent message = new CompanyInactivatedEvent(company.getId());

			companyEventPublisher.publishCompanyInactivated(message);
		});
	}

	//10. 재고 이벤트 처리
	@Transactional
	public void processLowStock(LowStockEvent event) {
		UUID productId = event.productId();
		UUID companyId = event.companyId();
		int requiredQuantity = event.requiredQuantity();

		//이벤트 발행
		StockReplenishedEvent message = new StockReplenishedEvent(
			productId,
			companyId,
			requiredQuantity
		);
		companyEventPublisher.publishReplenishStock(message);
	}
}
