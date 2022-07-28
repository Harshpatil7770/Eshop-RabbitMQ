package com.xoriant.shop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.shop.model.Product;
import com.xoriant.shop.model.Status;

public interface ProductRepo extends JpaRepository<Product, Long>{

	Optional<Product> findByProductName(String productName);

	List<Product> findByBrandName(String brandName);

	List<Product> findByCategoryName(String categoryName);

	List<Product> findByPriceIsBetween(double minPrice, double maxPrice);

	List<Product> findByPriceGreaterThan(double minRange);

	List<Product> findByPriceLessThan(double maxRange);

	List<Product> findByStatus(Status status);

}
