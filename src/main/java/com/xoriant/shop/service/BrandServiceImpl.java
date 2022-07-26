package com.xoriant.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xoriant.shop.dao.AdminRepo;
import com.xoriant.shop.dao.BrandDao;
import com.xoriant.shop.dto.BrandDTO;
import com.xoriant.shop.model.Admin;
import com.xoriant.shop.model.Brand;
import com.xoriant.shop.utility.CommonResponse;
import com.xoriant.shop.utility.Constant;
import com.xoriant.shop.utility.StatusCode;


@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private AdminRepo adminRepo;

	private Brand brand;

	@Override
	public CommonResponse<?> addNewBrand(Long adminId, String password, BrandDTO brandDTO) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<String>(Constant.WRONG_ADMIN_ID,
						StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<String>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.NOT_FOUND,
						HttpStatus.NOT_FOUND);
			}

			brand = new Brand();
			brand.setBrandName(brandDTO.getBrandName());
			brandDao.save(brand);
			return new CommonResponse<Brand>(brand, StatusCode.CREATED, HttpStatus.CREATED);
		} catch (Exception e) {
			return new CommonResponse<String>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
