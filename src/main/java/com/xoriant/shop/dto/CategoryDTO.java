package com.xoriant.shop.dto;

import java.util.List;

import com.xoriant.shop.model.Brand;
import com.xoriant.shop.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class CategoryDTO {

	private long categoryId;

	private String categoryName;

	private List<Brand> brandLists;

	private List<Product> productLists;

}
