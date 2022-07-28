package com.xoriant.shop.service;

import java.util.List;

import com.xoriant.shop.dto.BrandDTO;
import com.xoriant.shop.utility.CommonResponse;

public interface BrandService {

	CommonResponse<?> addNewBrand(Long adminId, String password, BrandDTO brandDTO);

	CommonResponse<?> updateBrand(Long adminId, String password, BrandDTO brandDTO);

	CommonResponse<?> findBrandById(Long adminId, String password, long brandId);

	CommonResponse<?> addNewListsOfBrands(Long adminId, String password, List<BrandDTO> brandDTO);

	CommonResponse<?> updateListOfBrand(Long adminId, String password, List<BrandDTO> brandDTO);

	CommonResponse<?> fetchAllBrands(Long adminId, String password);

	CommonResponse<?> findBrandBrandName(Long adminId, String password, String brandName);

	CommonResponse<?> findAllBrandAlphabeticalOrder(Long adminId, String password);

	CommonResponse<?> findAllBrandBasedOnFirstLetter(Long adminId, String password, String firstLetter);

	void deleteBrand(Long adminId, String password, long brandId);
}
