package com.xoriant.shop.service;

import com.xoriant.shop.dto.CategoryDTO;
import com.xoriant.shop.utility.CommonResponse;

public interface CategoryService {

	CommonResponse<?> addNewCategory(Long adminId, String password, CategoryDTO categoryDTO);

//	CommonResponse<?> updateCategory(Long adminId, String password, CategoryDTO categoryDTO);
//
//	CommonResponse<?> findCategoryById(Long adminId, String password, long categoryId);
//
//	List<CommonResponse<?>> addNewListsOfCategory(Long adminId, String password, List<CategoryDTO> categoryDTO);
//
//	List<CommonResponse<?>> updateListOfCategory(Long adminId, String password, List<CategoryDTO> categoryDTO);
//
//	List<CommonResponse<?>> fetchAllCategories();
//
//	CommonResponse<?> findCategoryById(Long adminId, String password, String categoryName);
}
