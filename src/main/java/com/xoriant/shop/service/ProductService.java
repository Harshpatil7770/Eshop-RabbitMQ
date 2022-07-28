package com.xoriant.shop.service;

import java.util.List;

import com.xoriant.shop.dto.ProductDTO;
import com.xoriant.shop.model.Status;
import com.xoriant.shop.utility.CommonResponse;

public interface ProductService {

	CommonResponse<?> addNewProduct(long adminId, String password, ProductDTO productDTO, long brandId,
			long categoryId);

	CommonResponse<?> updateProduct(long adminId, String password, ProductDTO productDTO, long brandId,
			long categoryId);

	CommonResponse<?> addNewListsProduct(long adminId, String password,List<ProductDTO> productDTOLists, long brandId,
			long categoryId);

	CommonResponse<?> updateListsProduct(long adminId, String password, List<ProductDTO> productDTOLists, long brandId,
			long categoryId);

	CommonResponse<?> findAllProducts(long adminId, String password);

	CommonResponse<?> findByProductId(long adminId, String password, long productId);

	CommonResponse<?> findByProductName(long adminId, String password, String productName);

	CommonResponse<?> findProductByBrandName(long adminId, String password, String brandName);

	CommonResponse<?> findProductByCategoryName(long adminId, String password, String categoryName);

	CommonResponse<?> findByPriceInBetween(long adminId, String password, double minPrice, double maxPrice);

	CommonResponse<?> findByPriceGretherThan(long adminId, String password, double minRange);

	CommonResponse<?> findByPriceLessThan(long adminId, String password, double maxRange);

	CommonResponse<?> findProductByStatus(long adminId, String password, Status status);

	CommonResponse<?> findAllProductsInAlphabeticalOrder(long adminId, String password);

	CommonResponse<?> findAllProductsWithUserEnteredFirstLetter(long adminId, String password, String firstLetter);

}
