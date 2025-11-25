package com.smartlogis.companyservice.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyRabbitConfig {

	//주문 생성 이벤트
	public static final String ORDER_CREATED_QUEUE = "smartlogis.order.created.queue";
	public static final String ORDER_CREATED_EXCHANGE = "smartlogis.order.exchange";
	public static final String ORDER_CREATED_ROUTING_KEY = "smartlogis.order.created";

	//업체에서 발행하는 상품 대상 이벤트
	public static final String COMPANY_ORDER_CREATED_QUEUE = "smartlogis.company.order.created.queue";
	public static final String COMPANY_ORDER_CREATED_EXCHANGE = "smartlogis.company.exchange";
	public static final String COMPANY_ORDER_CREATED_ROUTING_KEY = "smartlogis.company.order.created";

	@Bean
	public Queue orderCreatedQueue() {
		return new Queue(ORDER_CREATED_QUEUE, true);
	}

	@Bean
	public Queue companyOrderCreatedQueue() {
		return new Queue(COMPANY_ORDER_CREATED_QUEUE, true);
	}

	@Bean
	public TopicExchange orderExchange() {
		return new TopicExchange(ORDER_CREATED_EXCHANGE, true,  false);
	}

	@Bean
	public TopicExchange companyExchange() {
		return new TopicExchange(COMPANY_ORDER_CREATED_EXCHANGE, true, false);
	}

	@Bean
	public Binding orderCreatedBinding(Queue orderCreatedQueue, TopicExchange orderExchange) {
		return BindingBuilder.bind(orderCreatedQueue)
			.to(orderExchange)
			.with(ORDER_CREATED_ROUTING_KEY);
	}

	@Bean
	public Binding companyOrderCreatedBinding(Queue companyOrderCreatedQueue, TopicExchange companyExchange) {
		return BindingBuilder.bind(companyOrderCreatedQueue)
			.to(companyExchange)
			.with(COMPANY_ORDER_CREATED_ROUTING_KEY);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
