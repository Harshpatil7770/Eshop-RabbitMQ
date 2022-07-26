package com.xoriant.shop.dto;

import java.util.List;

import com.xoriant.shop.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

	private long brandId;

	private String brandName;

	private List<Product> productLists;
}
