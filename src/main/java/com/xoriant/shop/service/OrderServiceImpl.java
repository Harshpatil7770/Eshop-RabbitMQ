package com.xoriant.shop.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xoriant.shop.dao.OrderRepo;
import com.xoriant.shop.dao.ProductRepo;
import com.xoriant.shop.model.Order;
import com.xoriant.shop.model.Product;
import com.xoriant.shop.model.Status;
import com.xoriant.shop.utility.CommonResponse;
import com.xoriant.shop.utility.Constant;
import com.xoriant.shop.utility.StatusCode;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ProductRepo productRepo;

	@Override
	public CommonResponse<?> addNewOrder(long productId, int quantity) {
		try {
			Optional<Product> existingProduct = productRepo.findById(productId);
			if (!existingProduct.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			Order order = new Order();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			order.setOrderDate(dtf.format(now));
			order.setQuantity(quantity);
			if (existingProduct.get().getQuantity() < quantity) {
				return new CommonResponse<>(Constant.QUANTITY_NOT_AVALABLE, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			double totalAmount = quantity * existingProduct.get().getPrice();
			order.setTotalAmount(totalAmount);

			Product updateProduct = productRepo.findById(productId).orElse(null);
			if (updateProduct == null) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			int newQuantity = existingProduct.get().getQuantity() - quantity;
			updateProduct.setQuantity(newQuantity);
			if (newQuantity > 0) {
				updateProduct.setStatus(Status.AVAILABLE);
			} else {
				updateProduct.setStatus(Status.UNAVAILABLE);
			}
			productRepo.save(updateProduct);
			orderRepo.save(order);
			return new CommonResponse<>(order, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
