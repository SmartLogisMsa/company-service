package com.smartlogis.companyservice.domain.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.smartlogis.companyservice.application.service.CompanyService;
import com.smartlogis.companyservice.infrastructure.config.CompanyRabbitConfig;
import com.smartlogis.companyservice.interfaces.dto.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

	private final CompanyService companyService;

	@RabbitListener(queues = CompanyRabbitConfig.ORDER_CREATED_QUEUE)
	public void handleOrderCreated(OrderCreatedEvent event){
		log.info("[주문 생성] 이벤트 받음 {}", event);

		companyService.handleOrderCreatedEvent(event);

		log.info("상품에 대해 이벤트 생성 {}", event);
	}
}
