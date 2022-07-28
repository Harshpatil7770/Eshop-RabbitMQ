package com.xoriant.shop.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.shop.model.Brand;

public interface BrandDao extends JpaRepository<Brand, Long> {

	Optional<Brand> findByBrandName(String brandName);

}
