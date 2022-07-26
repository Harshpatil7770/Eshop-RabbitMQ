package com.xoriant.shop.service;

import com.xoriant.shop.dto.ProductDTO;
import com.xoriant.shop.utility.CommonResponse;

public interface ProductService {

	CommonResponse<?> addNewProduct(long adminId,String password,ProductDTO productDTO,long brandId,long categoryId);
}
