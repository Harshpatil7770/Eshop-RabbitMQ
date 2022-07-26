package com.xoriant.shop.dto;

import com.xoriant.shop.model.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private long productId;

	private String productName;

	private double price;

	private Status status;

	private int quantity;

	private String brandName;

	private String categoryName;
}
