package com.xoriant.shop.service;

import com.xoriant.shop.dto.BrandDTO;
import com.xoriant.shop.utility.CommonResponse;

public interface BrandService {

	CommonResponse<?> addNewBrand(Long adminId, String password, BrandDTO brandDTO);

//	CommonResponse<?> updateBrand(Long adminId, String password, BrandDTO brandDTO);
//
//	CommonResponse<?> findBrandById(Long adminId, String password, long brandId);
//
//	List<CommonResponse<?>> addNewListsOfBrands(Long adminId, String password, List<BrandDTO> brandDTO);
//
//	List<CommonResponse<?>> updateListOfBrand(Long adminId, String password, List<BrandDTO> brandDTO);
//
//	List<CommonResponse<?>> fetchAllBrands();
//
//	CommonResponse<?> findBrandById(Long adminId, String password, String brandName);
}
