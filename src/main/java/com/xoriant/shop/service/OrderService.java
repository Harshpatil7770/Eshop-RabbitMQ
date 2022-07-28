package com.xoriant.shop.service;

import com.xoriant.shop.utility.CommonResponse;

public interface OrderService {

	CommonResponse<?> addNewOrder(long userId, String password, long productId, int quantity);

	CommonResponse<?> cancelOrder(long userId, String password,long orderId);
}
