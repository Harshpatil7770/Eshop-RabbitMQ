package com.xoriant.shop.service;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xoriant.shop.dao.AdminRepo;
import com.xoriant.shop.dao.CategoryRepo;
import com.xoriant.shop.dto.CategoryDTO;
import com.xoriant.shop.model.Admin;
import com.xoriant.shop.model.Category;
import com.xoriant.shop.utility.CommonResponse;
import com.xoriant.shop.utility.Constant;
import com.xoriant.shop.utility.StatusCode;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public CommonResponse<?> addNewCategory(Long adminId, String password, CategoryDTO categoryDTO) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			Category category = new Category();
			category.setCategoryId(categoryDTO.getCategoryId());
			category.setCategoryName(categoryDTO.getCategoryName());
			categoryRepo.save(category);
			return new CommonResponse<>(category, StatusCode.CREATED, HttpStatus.CREATED);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> updateCategory(Long adminId, String password, CategoryDTO categoryDTO) {
		try {
			Optional<Category> existingCategory = categoryRepo.findById(categoryDTO.getCategoryId());
			if (!existingCategory.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}

			Category updateCategory = categoryRepo.findById(categoryDTO.getCategoryId()).orElse(null);
			if (updateCategory == null) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}

			updateCategory.setCategoryId(categoryDTO.getCategoryId());
			updateCategory.setCategoryName(categoryDTO.getCategoryName());
			categoryRepo.save(updateCategory);
			return new CommonResponse<>(updateCategory, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findCategoryById(Long adminId, String password, long categoryId) {
		try {

			Optional<Category> existingCategory = categoryRepo.findById(categoryId);
			if (!existingCategory.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}

			return new CommonResponse<>(existingCategory, StatusCode.OK, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public CommonResponse<?> addNewListsOfCategory(Long adminId, String password, List<CategoryDTO> categoryDTO) {
		Category category = null;
		try {

			for (CategoryDTO newCategory : categoryDTO) {
				Optional<Admin> existingAdmin = adminRepo.findById(adminId);
				if (!existingAdmin.isPresent()) {
					return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
				}
				if (!existingAdmin.get().getPassword().equals(password)) {
					return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.NOT_FOUND,
							HttpStatus.NOT_FOUND);
				}
			}
			for (CategoryDTO newCategory : categoryDTO) {

				category = new Category();
				category.setCategoryName(newCategory.getCategoryName());
				categoryRepo.save(category);
			}
			return new CommonResponse<>(Constant.NEW_LISTS_OF_CATEGORIES_ADDED, StatusCode.CREATED, HttpStatus.CREATED);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public CommonResponse<?> updateListOfCategory(Long adminId, String password, List<CategoryDTO> categoryDTO) {
		try {

			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			for (CategoryDTO categoryLists : categoryDTO) {
				Optional<Category> existingCategory = categoryRepo.findById(categoryLists.getCategoryId());
				if (!existingCategory.isPresent()) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
				}
			}

			for (CategoryDTO categoryLists : categoryDTO) {
				Category updateCategory = categoryRepo.findById(categoryLists.getCategoryId()).orElse(null);
				if (updateCategory == null) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
				}
				updateCategory.setCategoryId(categoryLists.getCategoryId());
				updateCategory.setCategoryName(categoryLists.getCategoryName());
				categoryRepo.save(updateCategory);
			}
			return new CommonResponse<>(Constant.UPDATED_LISTS_OF_CATEGORIES, StatusCode.CREATED, HttpStatus.CREATED);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> fetchAllCategories(Long adminId, String password) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Category> existingCategory = categoryRepo.findAll();
			if (!existingCategory.isEmpty()) {
				return new CommonResponse<List<Category>>(existingCategory, StatusCode.OK, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CommonResponse<String>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
	}

	@Override
	public CommonResponse<?> findByCategoryName(Long adminId, String password, String categoryName) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			Optional<Category> existingCategory = categoryRepo.findByCategoryName(categoryName);
			if (!existingCategory.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(existingCategory, StatusCode.OK, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}


}
