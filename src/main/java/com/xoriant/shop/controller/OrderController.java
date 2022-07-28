package com.xoriant.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.shop.msgsender.MessageSender;
import com.xoriant.shop.service.OrderService;
import com.xoriant.shop.utility.CommonResponse;
import com.xoriant.shop.utility.StatusCode;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private MessageSender messageSender;

	@PostMapping("/save/{userId}/{password}/{productId}/{quantity}")
	public CommonResponse<?> addNewOrder(@PathVariable long userId, @PathVariable String password,
			@PathVariable long productId, @PathVariable int quantity) {
		CommonResponse<?> response = orderService.addNewOrder(userId, password, productId, quantity);
		if (response.getStatusCode() == StatusCode.OK) {
			messageSender.addNewOrder("Order Placed Succesfully !");
		}
		return response;
	}

	@PostMapping("/update/{userId}/{password}/{orderId}")
	public CommonResponse<?> cancelOrder(@PathVariable long userId, @PathVariable String password,
			@PathVariable long orderId) {
		CommonResponse<?> response = orderService.cancelOrder(userId, password, orderId);
		if (response.getStatusCode() == StatusCode.OK) {
			messageSender.cancelOrder("ORDER ID: " + orderId + " Canceled Order Succesfully!");
		}
		return response;
	}
}
