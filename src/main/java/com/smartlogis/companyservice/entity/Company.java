package com.smartlogis.companyservice.entity;

import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.smartlogis.common.domain.AbstractEntity;
import com.smartlogis.companyservice.dto.request.CreateCompanyRequest;
import com.smartlogis.companyservice.exception.CompanyCode;
import com.smartlogis.companyservice.exception.CompanyException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_company")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Company extends AbstractEntity {

	// 업체 식별 ID
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	// 업체명
	@Column(nullable = false)
	private String name;

	// 업체 타입(CompanyType)
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CompanyType type;

	// 소속 허브 ID
	@Column(nullable = false)
	private UUID hubId;

	// 업체 주소
	@Column(nullable = false)
	private String address;

	// 업체 상태(CompanyStatus)
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CompanyStatus status;

	// 업체 담당자 ID
	@Column(nullable = false)
	private UUID managerId;

	//======================================

	// 1. 상태 변경 메서드
	// 업체 상태를 비활성 -> 활성
	public void activate(){
		if(this.status == CompanyStatus.ACTIVE){
			return;
		}
		this.status = CompanyStatus.ACTIVE;
	}

	// 업체 상태를 활성 -> 비활성
	public void inactivate(){
		if(this.status == CompanyStatus.INACTIVE){
			return;
		}
		this.status = CompanyStatus.INACTIVE;
	}

	public void updateStatus(CompanyStatus newStatus) {
		if (newStatus == null) {
			throw new CompanyException(CompanyCode.INVALID_STATUS);
		}

		if (this.status == newStatus) {
			return;
		}

		this.status = newStatus;
	}

	// 업체 삭제 시 업체 상태 비활성화
	@Override
	public void delete(){
		super.delete();
		this.inactivate();
	}

	//======================================

	// 2. 데이터 변경 메서드
	// 업체명 변경
	public void changeName(String newName){
		validateName(newName);
		this.name = newName;
	}

	// 업체 주소 변경
	public void changeAddress(String newAddress){
		validateAddress(newAddress);
		this.address = newAddress;
	}

	// 업체 타입 변경
	public void changeType(CompanyType newType){
		validateType(newType);
		if(this.type == newType){
			return;
		}
		this.type = newType;
	}

	// 담당 허브 변경
	public void changeHub(UUID newHubId){
		validateHubId(newHubId);
		this.hubId = newHubId;
	}

	// 담당자 id 변경
	public void changeManagerId(UUID newManagerId){
		validateManagerId(newManagerId);
		this.managerId = newManagerId;
	}

	//======================================

	// 3. 검증 메서드
	// 업체명 검증
	private static void validateName(String name) {
		if (name == null || name.isBlank()) {
			throw new CompanyException(CompanyCode.INVALID_NAME);
		}
	}

	// 주소 검증
	private static void validateAddress(String address) {
		if (address == null || address.isBlank()) {
			throw new CompanyException(CompanyCode.INVALID_ADDRESS);
		}
	}

	// 업체 타입 검증
	private static void validateType(CompanyType type) {
		if (type == null) {
			throw new CompanyException(CompanyCode.INVALID_TYPE);
		}
	}

	//허브 아이디 검증
	private static void validateHubId(UUID hubId) {
		if(hubId == null) {
			throw new CompanyException(CompanyCode.INVALID_HUBID);
		}
	}

	//담당자 아이디 검증
	private static void validateManagerId(UUID managerId) {
		if(managerId == null) {
			throw new CompanyException(CompanyCode.INVALID_MANAGERID);
		}
	}


	//======================================

	// 4. 생성 메서드
	// 업체 생성
	public static Company create(String name, CompanyType type, UUID hubId, String address, UUID managerId){
		validateName(name);
		validateAddress(address);
		validateType(type);
		validateHubId(hubId);
		validateManagerId(managerId);

		return Company.builder()
			.name(name)
			.type(type)
			.hubId(hubId)
			.address(address)
			.status(CompanyStatus.ACTIVE)
			.managerId(managerId)
			.build();
	}

	public static Company create(CreateCompanyRequest request){
		return create(
			request.getName(),
			request.getType(),
			request.getHubId(),
			request.getAddress(),
			request.getManagerId()
		);
	}

}
