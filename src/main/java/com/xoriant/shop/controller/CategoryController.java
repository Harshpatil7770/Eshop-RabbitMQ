package com.xoriant.shop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PutMapping("/update/{adminId}/{password}")
	public CommonResponse<?> updateCategory(@PathVariable Long adminId, @PathVariable String password,
			@RequestBody CategoryDTO categoryDTO) {
		return categoryService.updateCategory(adminId, password, categoryDTO);
	}

	@GetMapping("/find/{adminId}/{password}/{categoryId}")
	public CommonResponse<?> findCategoryById(@PathVariable Long adminId, @PathVariable String password,
			@PathVariable long categoryId) {
		return categoryService.findCategoryById(adminId, password, categoryId);
	}

	@PostMapping("/saveAll/{adminId}/{password}")
	public CommonResponse<?> addNewListsOfCategory(@Valid @PathVariable Long adminId, @PathVariable String password,
			@RequestBody List<CategoryDTO> categoryDTO) {
		return categoryService.addNewListsOfCategory(adminId, password, categoryDTO);
	}

	@PutMapping("/updateAll/{adminId}/{password}")
	public CommonResponse<?> updateListOfCategory(@Valid @PathVariable Long adminId, @PathVariable String password,
			@RequestBody List<CategoryDTO> categoryDTO) {
		return categoryService.updateListOfCategory(adminId, password, categoryDTO);
	}

	@GetMapping("/findAll/{adminId}/{password}")
	public CommonResponse<?> fetchAllCategories(@PathVariable Long adminId, @PathVariable String password) {
		return categoryService.fetchAllCategories(adminId, password);
	}

	@GetMapping("find-category/{adminId}/{password}/{categoryName}")
	public CommonResponse<?> findByCategoryName(@PathVariable Long adminId, @PathVariable String password,
			@PathVariable String categoryName) {
		return categoryService.findByCategoryName(adminId, password, categoryName);
	}
}
