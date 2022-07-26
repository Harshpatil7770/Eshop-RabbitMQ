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
}
