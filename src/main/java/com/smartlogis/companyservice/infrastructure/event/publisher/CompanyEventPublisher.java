package com.smartlogis.companyservice.infrastructure.event.publisher;

import static com.smartlogis.companyservice.infrastructure.config.CompanyRabbitConfig.*;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.smartlogis.companyservice.interfaces.dto.event.CompanyHubChangeEvent;
import com.smartlogis.companyservice.interfaces.dto.event.CompanyInactivatedEvent;
import com.smartlogis.companyservice.interfaces.dto.event.CompanyOrderCreatedEvent;
import com.smartlogis.companyservice.interfaces.dto.event.CompanyStatusChangedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CompanyEventPublisher {

	private final RabbitTemplate rabbitTemplate;

	public void publishToProduct(CompanyOrderCreatedEvent event) {
		rabbitTemplate.convertAndSend(
			COMPANY_ORDER_CREATED_EXCHANGE,
			COMPANY_ORDER_CREATED_ROUTING_KEY,
			event
		);
	}

	public void publishCompanyInactivated(CompanyInactivatedEvent event) {
		rabbitTemplate.convertAndSend(
			COMPANY_INACTIVATED_EXCHANGE,
			COMPANY_INACTIVATED_ROUTING_KEY,
			event
		);
	}

	public void publishStatusChanged(CompanyStatusChangedEvent event) {
		rabbitTemplate.convertAndSend(
			COMPANY_STATUS_CHANGED_EXCHANGE,
			COMPANY_STATUS_CHANGED_ROUTING_KEY,
			event
		);
	}

	public void publishChangedHubId(CompanyHubChangeEvent event) {
		rabbitTemplate.convertAndSend(
			COMPANY_HUB_CHANGED_EXCHANGE,
			COMPANY_HUB_CHANGED_ROUTING_KEY,
			event
		);
	}
}
