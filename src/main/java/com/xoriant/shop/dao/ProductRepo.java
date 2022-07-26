package com.xoriant.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.shop.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
