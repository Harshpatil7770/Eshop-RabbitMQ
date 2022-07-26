package com.xoriant.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.shop.dto.ProductDTO;
import com.xoriant.shop.service.ProductService;
import com.xoriant.shop.utility.CommonResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/save/{adminId}/{password}/{brandId}/{categoryId}")
	public CommonResponse<?> adminId(@PathVariable long adminId, @PathVariable String password,
			@RequestBody ProductDTO productDTO, @PathVariable long brandId, @PathVariable long categoryId) {
		return productService.addNewProduct(adminId, password, productDTO, brandId, categoryId);
	}
}
