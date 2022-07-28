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
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
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

	@Override
	public CommonResponse<?> updateProduct(long adminId, String password, ProductDTO productDTO, long brandId,
			long categoryId) {
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
			Optional<Category> existingCategory = categoryRepo.findById(categoryId);
			if (!existingCategory.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			Optional<Product> existingProduct = productRepo.findById(productDTO.getProductId());
			if (!existingProduct.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			Product updateProduct = productRepo.findById(productDTO.getProductId()).orElse(null);
			updateProduct.setProductId(productDTO.getProductId());
			updateProduct.setProductName(productDTO.getProductName());
			updateProduct.setPrice(productDTO.getPrice());
			updateProduct.setQuantity(productDTO.getQuantity());
			if (productDTO.getQuantity() > 0) {
				updateProduct.setStatus(Status.AVAILABLE);
			} else {
				updateProduct.setStatus(Status.UNAVAILABLE);
			}
			Brand updateBrand = brandDao.findById(brandId).orElse(null);
			if (updateBrand == null) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			updateProduct.setBrandName(updateBrand.getBrandName());
			Category updateCategory = categoryRepo.findById(categoryId).orElse(null);
			if (updateCategory == null) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			updateProduct.setCategoryName(updateCategory.getCategoryName());

			productRepo.save(updateProduct);

			return new CommonResponse<>(updateProduct, StatusCode.CREATED, HttpStatus.CREATED);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> addNewListsProduct(long adminId, String password, List<ProductDTO> productDTOLists,
			long brandId, long categoryId) {
		List<Product> productLists = new ArrayList<>();
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
			Optional<Category> existingCategory = categoryRepo.findById(categoryId);
			if (!existingCategory.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			for (ProductDTO eachNewProduct : productDTOLists) {
				Product newProduct = new Product();
				newProduct.setProductId(eachNewProduct.getProductId());
				newProduct.setProductName(eachNewProduct.getProductName());
				newProduct.setPrice(eachNewProduct.getPrice());
				newProduct.setQuantity(eachNewProduct.getQuantity());
				if (eachNewProduct.getQuantity() > 0) {
					newProduct.setStatus(Status.AVAILABLE);
				} else {
					newProduct.setStatus(Status.UNAVAILABLE);
				}

				Brand checkBrand = brandDao.findById(brandId).orElse(null);
				if (checkBrand == null) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
							HttpStatus.BAD_REQUEST);
				}
				newProduct.setBrandName(checkBrand.getBrandName());

				Category checkCategory = categoryRepo.findById(categoryId).orElse(null);
				if (checkCategory == null) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
							HttpStatus.BAD_REQUEST);
				}
				newProduct.setCategoryName(checkCategory.getCategoryName());
				productRepo.save(newProduct);
				productLists.add(newProduct);
			}
			return new CommonResponse<>(productLists, StatusCode.CREATED, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public CommonResponse<?> updateListsProduct(long adminId, String password, List<ProductDTO> productDTOLists,
			long brandId, long categoryId) {
		List<Product> updateProductLists = new ArrayList<>();
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
			Optional<Category> existingCategory = categoryRepo.findById(categoryId);
			if (!existingCategory.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			for (ProductDTO eachExistingProduct : productDTOLists) {

				Optional<Product> checkProduct = productRepo.findById(eachExistingProduct.getProductId());
				if (!checkProduct.isPresent()) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
							HttpStatus.BAD_REQUEST);
				}
			}

			for (ProductDTO eachExistingProduct : productDTOLists) {
				Product updateProduct = productRepo.findById(eachExistingProduct.getProductId()).orElse(null);
				if (updateProduct == null) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
							HttpStatus.BAD_REQUEST);
				}
				updateProduct.setProductId(eachExistingProduct.getProductId());
				updateProduct.setProductName(eachExistingProduct.getProductName());
				updateProduct.setPrice(eachExistingProduct.getPrice());
				updateProduct.setQuantity(eachExistingProduct.getQuantity());
				if (eachExistingProduct.getQuantity() > 0) {
					updateProduct.setStatus(Status.AVAILABLE);
				} else {
					updateProduct.setStatus(Status.UNAVAILABLE);
				}

				Brand checkBrand = brandDao.findById(brandId).orElse(null);
				if (checkBrand == null) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
							HttpStatus.BAD_REQUEST);
				}
				updateProduct.setBrandName(checkBrand.getBrandName());

				Category checkCategory = categoryRepo.findById(categoryId).orElse(null);
				if (checkCategory == null) {
					return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
							HttpStatus.BAD_REQUEST);
				}
				updateProduct.setCategoryName(checkCategory.getCategoryName());
				productRepo.save(updateProduct);
				updateProductLists.add(updateProduct);

			}
			return new CommonResponse<>(updateProductLists, StatusCode.CREATED, HttpStatus.CREATED);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public CommonResponse<?> findAllProducts(long adminId, String password) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> productLists = productRepo.findAll();
			if (productLists.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(productLists, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public CommonResponse<?> findByProductId(long adminId, String password, long productId) {

		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			Optional<Product> existingProduct = productRepo.findById(productId);
			if (!existingProduct.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(existingProduct, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public CommonResponse<?> findByProductName(long adminId, String password, String productName) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			Optional<Product> existingProduct = productRepo.findByProductName(productName);
			if (!existingProduct.isPresent()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			return new CommonResponse<>(existingProduct, StatusCode.OK, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findProductByBrandName(long adminId, String password, String brandName) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> existingProduct = productRepo.findByBrandName(brandName);
			if (existingProduct.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			return new CommonResponse<>(existingProduct, StatusCode.OK, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findProductByCategoryName(long adminId, String password, String categoryName) {

		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> existingProduct = productRepo.findByCategoryName(categoryName);
			if (existingProduct.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			return new CommonResponse<>(existingProduct, StatusCode.OK, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findByPriceInBetween(long adminId, String password, double minPrice, double maxPrice) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> existingProduct = productRepo.findByPriceIsBetween(minPrice, maxPrice);
			if (existingProduct.isEmpty()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(existingProduct, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findByPriceGretherThan(long adminId, String password, double minRange) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> existingProduct = productRepo.findByPriceGreaterThan(minRange);
			if (existingProduct.isEmpty()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(existingProduct, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findByPriceLessThan(long adminId, String password, double maxRange) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> existingProduct = productRepo.findByPriceLessThan(maxRange);
			if (existingProduct.isEmpty()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(existingProduct, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findProductByStatus(long adminId, String password, Status status) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> existingProduct = productRepo.findByStatus(status);
			if (existingProduct.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}
			return new CommonResponse<>(existingProduct, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findAllProductsInAlphabeticalOrder(long adminId, String password) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> productLists = productRepo.findAll();
			if (productLists.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			List<Product> alphabeticalOrderProductLists = productLists.stream()
					.sorted((e1, e2) -> e1.getProductName().compareTo(e2.getProductName()))
					.collect(Collectors.toList());
			return new CommonResponse<>(alphabeticalOrderProductLists, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> findAllProductsWithUserEnteredFirstLetter(long adminId, String password,
			String firstLetter) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			if (!existingAdmin.get().getPassword().equals(password)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST);
			}

			List<Product> productLists = productRepo.findAll();
			if (productLists.isEmpty()) {
				return new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			List<Product> firstLetterEnteredProducts = productLists.stream()
					.filter(e -> e.getProductName().startsWith(firstLetter)).collect(Collectors.toList());
			return new CommonResponse<>(firstLetterEnteredProducts, StatusCode.OK, HttpStatus.OK);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
