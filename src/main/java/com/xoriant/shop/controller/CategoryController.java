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

	@PostMapping("/{adminId}/{password}/save/")
	public CommonResponse<?> addNewCategory(@Valid @PathVariable Long adminId, @PathVariable String password,
			@RequestBody CategoryDTO categoryDTO) {
		return categoryService.addNewCategory(adminId, password, categoryDTO);
	}

	@PutMapping("/{adminId}/{password}/update")
	public CommonResponse<?> updateCategory(@PathVariable Long adminId, @PathVariable String password,
			@RequestBody CategoryDTO categoryDTO) {
		return categoryService.updateCategory(adminId, password, categoryDTO);
	}

	@GetMapping("/{adminId}/{password}/find/{categoryId}")
	public CommonResponse<?> findCategoryById(@PathVariable Long adminId, @PathVariable String password,
			@PathVariable long categoryId) {
		return categoryService.findCategoryById(adminId, password, categoryId);
	}

	@PostMapping("/{adminId}/{password}/saveAll")
	public CommonResponse<?> addNewListsOfCategory(@Valid @PathVariable Long adminId, @PathVariable String password,
			@RequestBody List<CategoryDTO> categoryDTO) {
		return categoryService.addNewListsOfCategory(adminId, password, categoryDTO);
	}

	@PutMapping("/{adminId}/{password}//updateAll")
	public CommonResponse<?> updateListOfCategory(@Valid @PathVariable Long adminId, @PathVariable String password,
			@RequestBody List<CategoryDTO> categoryDTO) {
		return categoryService.updateListOfCategory(adminId, password, categoryDTO);
	}

	@GetMapping("/{adminId}/{password}/findAll")
	public CommonResponse<?> fetchAllCategories(@PathVariable Long adminId, @PathVariable String password) {
		return categoryService.fetchAllCategories(adminId, password);
	}

	@GetMapping("/{adminId}/{password}/find-category/{categoryName}")
	public CommonResponse<?> findByCategoryName(@PathVariable Long adminId, @PathVariable String password,
			@PathVariable String categoryName) {
		return categoryService.findByCategoryName(adminId, password, categoryName);
	}

	@GetMapping("/{adminId}/{password}/findAll/alphabeticalOrder")
	public CommonResponse<?> findAllCategoritesInAlphabeticalOrder(@PathVariable Long adminId,
			@PathVariable String password) {
		return categoryService.findAllCategoritesInAlphabeticalOrder(adminId, password);
	}

	@GetMapping("/{adminId}/{password}/findAllBy/{firstLetter}")
	public CommonResponse<?> findAllCategoritesWithStartingCharacter(@PathVariable Long adminId,
			@PathVariable String password, @PathVariable String firstLetter) {
		return categoryService.findAllCategoritesWithStartingCharacter(adminId, password, firstLetter);
	}

}
