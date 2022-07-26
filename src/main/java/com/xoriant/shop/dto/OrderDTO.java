package com.xoriant.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private long orderId;

	private String date;

	private int quantity;

	private double totalAmount;
}
