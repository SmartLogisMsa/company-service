package com.smartlogis.companyservice.domain.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.smartlogis.companyservice.application.service.CompanyService;
import com.smartlogis.companyservice.infrastructure.config.CompanyRabbitConfig;
import com.smartlogis.companyservice.interfaces.dto.event.HubDeletedMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class HubEventListener {

	private final CompanyService companyService;

	@RabbitListener(queues = CompanyRabbitConfig.HUB_DELETED_QUEUE)
	public void handleHubDeleted(HubDeletedMessage event){

		companyService.handleHubDeleted(event);
		log.info("허브 삭제 이벤트 처리 완료");
	}
}
