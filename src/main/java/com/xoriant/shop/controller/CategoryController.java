package com.xoriant.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.shop.dto.CategoryDTO;
import com.xoriant.shop.service.CategoryService;
import com.xoriant.shop.utility.CommonResponse;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/save/{adminId}/{password}")
	public CommonResponse<?> addNewCategory(@Valid @PathVariable Long adminId, @PathVariable String password,
			@RequestBody CategoryDTO categoryDTO) {
		return categoryService.addNewCategory(adminId, password, categoryDTO);
	}
}
