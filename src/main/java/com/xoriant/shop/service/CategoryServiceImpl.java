package com.xoriant.shop.service;

import java.util.Optional;

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

	private Category category;

	@Override
	public CommonResponse<?> addNewCategory(Long adminId, String password, CategoryDTO categoryDTO) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<String>(Constant.WRONG_ADMIN_ID, StatusCode.NOT_FOUND,
						HttpStatus.NOT_FOUND);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<String>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			category = new Category();
			category.setCategoryName(categoryDTO.getCategoryName());
			categoryRepo.save(category);
			return new CommonResponse<Category>(category, StatusCode.CREATED, HttpStatus.CREATED);
		} catch (Exception e) {
			return new CommonResponse<String>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
