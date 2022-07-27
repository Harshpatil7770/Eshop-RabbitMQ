package com.xoriant.shop.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.shop.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

	Optional<Category> findByCategoryName(String categoryName);

}
