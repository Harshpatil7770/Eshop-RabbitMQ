package com.xoriant.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public CommonResponse<?> addNewBrand(Long adminId, String password, BrandDTO brandDTO) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			Brand brand = new Brand();
			brand.setBrandName(brandDTO.getBrandName());
			brandDao.save(brand);
			return new CommonResponse<>(brand, StatusCode.CREATED, HttpStatus.CREATED);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> updateBrand(Long adminId, String password, BrandDTO brandDTO) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			Optional<Brand> existingBrand = brandDao.findById(brandDTO.getBrandId());
			if (!existingBrand.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			Brand updateBrand = brandDao.findById(brandDTO.getBrandId()).orElse(null);
			updateBrand.setBrandId(brandDTO.getBrandId());
			updateBrand.setBrandName(brandDTO.getBrandName());
			brandDao.save(updateBrand);
			return new CommonResponse<>(updateBrand, StatusCode.CREATED, HttpStatus.CREATED);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@Override
	public CommonResponse<?> findBrandById(Long adminId, String password, long brandId) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			Optional<Brand> existingBrand = brandDao.findById(brandId);
			if (!existingBrand.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(existingBrand, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> addNewListsOfBrands(Long adminId, String password, List<BrandDTO> brandDTO) {
		List<Brand> brandLists = new ArrayList<>();
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			for (BrandDTO newBrand : brandDTO) {
				Brand brand = new Brand();
				brand.setBrandId(newBrand.getBrandId());
				brand.setBrandName(newBrand.getBrandName());
				brandDao.save(brand);
				brandLists.add(brand);
			}
			return new CommonResponse<>(brandLists, StatusCode.CREATED, HttpStatus.CREATED);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public CommonResponse<?> updateListOfBrand(Long adminId, String password, List<BrandDTO> brandDTO) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			for (BrandDTO newBrand : brandDTO) {
				Optional<Brand> existingBrand = brandDao.findById(newBrand.getBrandId());
				if (!existingBrand.isPresent()) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
							HttpStatus.BAD_REQUEST);
				}
			}
			for (BrandDTO newBrand : brandDTO) {
				Brand updateBrand = brandDao.findById(newBrand.getBrandId()).orElse(null);
				if (updateBrand == null) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
							HttpStatus.BAD_REQUEST);
				}
				updateBrand.setBrandId(newBrand.getBrandId());
				updateBrand.setBrandName(newBrand.getBrandName());
				brandDao.save(updateBrand);
			}
			return new CommonResponse<>(Constant.UPDATED_LISTS_OF_BRANDS, StatusCode.CREATED, HttpStatus.CREATED);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> fetchAllBrands(Long adminId, String password) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Brand> brandLists = brandDao.findAll();
			if (brandLists.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(brandLists, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findBrandBrandName(Long adminId, String password, String brandName) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			Optional<Brand> existingBrand = brandDao.findByBrandName(brandName);
			if (!existingBrand.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(existingBrand, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findAllBrandAlphabeticalOrder(Long adminId, String password) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Brand> brandLists = brandDao.findAll();
			if (brandLists.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			List<Brand> alphabeticalOrderBrandLists = brandLists.stream()
					.sorted((e1, e2) -> e1.getBrandName().compareTo(e2.getBrandName())).collect(Collectors.toList());
			return new CommonResponse<>(alphabeticalOrderBrandLists, StatusCode.OK, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findAllBrandBasedOnFirstLetter(Long adminId, String password, String firstLetter) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Brand> brandLists = brandDao.findAll();
			if (brandLists.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			List<Brand> alphabeticalOrderBrandLists = brandLists.stream()
					.filter(e -> e.getBrandName().startsWith(firstLetter)).collect(Collectors.toList());
			return new CommonResponse<>(alphabeticalOrderBrandLists, StatusCode.OK, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteBrand(Long adminId, String password, long brandId) {
		// TODO Auto-generated method stub
		
	}

}
