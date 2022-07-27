package com.xoriant.shop.service;

import java.util.List;

import com.xoriant.shop.dto.CategoryDTO;
import com.xoriant.shop.utility.CommonResponse;

public interface CategoryService {

	CommonResponse<?> addNewCategory(Long adminId, String password, CategoryDTO categoryDTO);

	CommonResponse<?> updateCategory(Long adminId, String password, CategoryDTO categoryDTO);

	CommonResponse<?> findCategoryById(Long adminId, String password, long categoryId);

	CommonResponse<?> addNewListsOfCategory(Long adminId, String password, List<CategoryDTO> categoryDTO);

	CommonResponse<?> updateListOfCategory(Long adminId, String password, List<CategoryDTO> categoryDTO);

	CommonResponse<?> fetchAllCategories(Long adminId, String password);

	CommonResponse<?> findByCategoryName(Long adminId, String password, String categoryName);
}
