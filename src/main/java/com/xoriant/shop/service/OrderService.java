package com.xoriant.shop.service;

import com.xoriant.shop.utility.CommonResponse;

public interface OrderService {

	CommonResponse<?> addNewOrder(long productId,int quantity);
}
