package com.xoriant.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xoriant.shop.dao.AdminRepo;
import com.xoriant.shop.dao.BrandDao;
import com.xoriant.shop.dao.CategoryRepo;
import com.xoriant.shop.dao.ProductRepo;
import com.xoriant.shop.dto.ProductDTO;
import com.xoriant.shop.model.Admin;
import com.xoriant.shop.model.Brand;
import com.xoriant.shop.model.Category;
import com.xoriant.shop.model.Product;
import com.xoriant.shop.model.Status;
import com.xoriant.shop.utility.CommonResponse;
import com.xoriant.shop.utility.Constant;
import com.xoriant.shop.utility.StatusCode;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CommonResponse<?> addNewProduct(long adminId, String password, ProductDTO productDTO, long brandId,
			long categoryId) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			Optional<Brand> existingBrand = brandDao.findById(brandId);
			if (!existingBrand.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			Optional<Category> existingCategory = categoryRepo.findById(categoryId);
			if (!existingCategory.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}

			Product product = new Product();
			product.setProductName(productDTO.getProductName());
			product.setPrice(productDTO.getPrice());
			product.setQuantity(productDTO.getQuantity());
			if (productDTO.getQuantity() > 0) {
				product.setStatus(Status.AVAILABLE);
			} else {
				product.setStatus(Status.UNAVAILABLE);
			}
			product.setStatus(Status.AVAILABLE);
			product.setBrandName(existingBrand.get().getBrandName());
			product.setCategoryName(existingCategory.get().getCategoryName());

			productRepo.save(product);
			return new CommonResponse<>(product, StatusCode.CREATED, HttpStatus.CREATED);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
