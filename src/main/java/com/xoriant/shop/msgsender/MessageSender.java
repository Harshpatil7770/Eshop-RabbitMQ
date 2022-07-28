package com.xoriant.shop.msgsender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	public Queue changePassword() {
		return new Queue("passwordChangeQ", false);
	}

	public void changePassword(String msg) {
		rabbitTemplate.convertAndSend("passwordChangeQ", msg);
	}

	@Bean
	public Queue addNewOrder() {
		return new Queue("orderPlacedQ", false);
	}

	public void addNewOrder(String msg) {
		rabbitTemplate.convertAndSend("orderPlacedQ", msg);
	}

	@Bean
	public Queue cancelOrder() {
		return new Queue("cancelOrderQ", false);
	}

	public void cancelOrder(String msg) {
		rabbitTemplate.convertAndSend("cancelOrderQ", msg);
	}
}
