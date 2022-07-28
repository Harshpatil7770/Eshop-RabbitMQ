package com.xoriant.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.shop.dto.ProductDTO;
import com.xoriant.shop.model.Status;
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

	@PutMapping("/update/{adminId}/{password}/{brandId}/{categoryId}")
	public CommonResponse<?> updateProduct(@PathVariable long adminId, @PathVariable String password,
			@RequestBody ProductDTO productDTO, @PathVariable long brandId, @PathVariable long categoryId) {
		return productService.updateProduct(adminId, password, productDTO, brandId, categoryId);

	}

	@PostMapping("/saveAll/{adminId}/{password}/{brandId}/{categoryId}")
	public CommonResponse<?> addNewListsProduct(@PathVariable long adminId, @PathVariable String password,
			@RequestBody List<ProductDTO> productDTOLists, @PathVariable long brandId, @PathVariable long categoryId) {
		return productService.addNewListsProduct(adminId, password, productDTOLists, brandId, categoryId);

	}

	@PutMapping("/updateAll/{adminId}/{password}/{brandId}/{categoryId}")
	public CommonResponse<?> updateListsProduct(@PathVariable long adminId, @PathVariable String password,
			@RequestBody List<ProductDTO> productDTOLists, @PathVariable long brandId, @PathVariable long categoryId) {
		return productService.updateListsProduct(adminId, password, productDTOLists, brandId, categoryId);
	}

	@GetMapping("/findAll/{adminId}/{password}")
	public CommonResponse<?> findAllProducts(@PathVariable long adminId, @PathVariable String password) {
		return productService.findAllProducts(adminId, password);
	}

	@GetMapping("/find/{adminId}/{password}/{productId}")
	public CommonResponse<?> findByProductId(@PathVariable long adminId, @PathVariable String password,
			@PathVariable long productId) {
		return productService.findByProductId(adminId, password, productId);
	}

	@GetMapping("/find/product/{adminId}/{password}/{productName}")
	public CommonResponse<?> findByProductName(@PathVariable long adminId, @PathVariable String password,
			@PathVariable String productName) {
		return productService.findByProductName(adminId, password, productName);
	}

	@GetMapping("/find-product/brandName/{adminId}/{password}/{brandName}")
	public CommonResponse<?> findProductByBrandName(@PathVariable long adminId, @PathVariable String password,
			@PathVariable String brandName) {
		return productService.findProductByBrandName(adminId, password, brandName);
	}

	@GetMapping("/find-product/categoryName/{adminId}/{password}/{categoryName}")
	public CommonResponse<?> findProductByCategoryName(@PathVariable long adminId, @PathVariable String password,
			@PathVariable String categoryName) {
		return productService.findProductByCategoryName(adminId, password, categoryName);
	}

	@GetMapping("/find-product/{adminId}/{password}/{minPrice}/{maxPrice}")
	public CommonResponse<?> findByPriceInBetween(@PathVariable long adminId, @PathVariable String password,
			@PathVariable double minPrice, @PathVariable double maxPrice) {
		return productService.findByPriceInBetween(adminId, password, minPrice, maxPrice);
	}

	@GetMapping("/find-product/{adminId}/{password}/minPrice/{minRange}")
	public CommonResponse<?> findByPriceGretherThan(@PathVariable long adminId, @PathVariable String password,
			@PathVariable double minRange) {
		return productService.findByPriceGretherThan(adminId, password, minRange);
	}

	@GetMapping("/find-product/maxPrice/{adminId}/{password}/{maxRange}")
	public CommonResponse<?> findByPriceLessThan(@PathVariable long adminId, @PathVariable String password,
			@PathVariable double maxRange) {
		return productService.findByPriceLessThan(adminId, password, maxRange);
	}

	@GetMapping("/find-product/status/{adminId}/{password}/{status}")
	public CommonResponse<?> findProductByStatus(@PathVariable long adminId, @PathVariable String password,
			@PathVariable String status) {
		return productService.findProductByStatus(adminId, password, Status.valueOf(status));
	}

	@GetMapping("/findAll-alphabeticalOrder/{adminId}/{password}")
	public CommonResponse<?> findAllProductsInAlphabeticalOrder(@PathVariable long adminId,
			@PathVariable String password) {
		return productService.findAllProductsInAlphabeticalOrder(adminId, password);
	}

	@GetMapping("/findAll/{adminId}/{password}/{firstLetter}")
	public CommonResponse<?> findAllProductsWithUserEnteredFirstLetter(@PathVariable long adminId,
			@PathVariable String password, @PathVariable String firstLetter) {
		return productService.findAllProductsWithUserEnteredFirstLetter(adminId, password, firstLetter);
	}
}
