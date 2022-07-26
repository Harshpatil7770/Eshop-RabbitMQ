package com.xoriant.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.shop.service.OrderService;
import com.xoriant.shop.utility.CommonResponse;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/save/{productId}/{quantity}")
	public CommonResponse<?> addNewOrder(@PathVariable long productId, @PathVariable int quantity) {
		return orderService.addNewOrder(productId, quantity);
	}

}
