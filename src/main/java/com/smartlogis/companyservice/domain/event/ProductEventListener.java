package com.smartlogis.companyservice.domain.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.smartlogis.companyservice.application.service.CompanyService;
import com.smartlogis.companyservice.infrastructure.config.CompanyRabbitConfig;
import com.smartlogis.companyservice.interfaces.dto.event.LowStockEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductEventListener {

	private final CompanyService companyService;

	@RabbitListener(queues = CompanyRabbitConfig.PRODUCT_LOW_STOCK_QUEUE)
	public void handleLowStockEvent(LowStockEvent event) {
		log.info("[재고부족] 이벤트 수신: {}", event);

		companyService.processLowStock(event);

		log.info("[재고보충] 이벤트 발행");
	}
}
