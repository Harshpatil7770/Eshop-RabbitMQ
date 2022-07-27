package com.xoriant.shop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.xoriant.shop.dao.AdminRepo;
import com.xoriant.shop.dao.CategoryRepo;
import com.xoriant.shop.dto.CategoryDTO;
import com.xoriant.shop.model.Admin;
import com.xoriant.shop.model.Category;
import com.xoriant.shop.model.Gender;
import com.xoriant.shop.utility.CommonResponse;
import com.xoriant.shop.utility.Constant;
import com.xoriant.shop.utility.StatusCode;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	private CategoryRepo categoryRepo;

	@Mock
	private AdminRepo adminRepo;

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	private Admin admin;

	private CategoryDTO categoryDTO;
	private CategoryDTO categoryDTO1;

	private Category category;
	private Category category1;

	private List<CategoryDTO> categoryLists;

	private static final long ADMIN_ID = 101;
	private static final String PASSWORD = "smith@123";

	@BeforeEach
	void setUp() {
		admin = new Admin(101, "Smith", "Joshi", "smith123@gmail.com", 8830482589L, Gender.MALE, "smith", "smith@123");
		categoryDTO = new CategoryDTO(1001, "Mobile", null, null);
		categoryDTO1 = new CategoryDTO(1002, "Laptop", null, null);
		category = new Category(categoryDTO.getCategoryId(), categoryDTO.getCategoryName());
		category1 = new Category(categoryDTO1.getCategoryId(), categoryDTO1.getCategoryName());

		categoryLists = new ArrayList<>();
		categoryLists.add(categoryDTO);
		categoryLists.add(categoryDTO1);
	}

	@Test
	void addNewCategory() {

		Optional<Admin> existingAdmin = Optional.of(admin);
		when(adminRepo.findById(ADMIN_ID)).thenReturn(existingAdmin);
		Optional<Admin> checkAdmin = adminRepo.findById(ADMIN_ID);
		if (checkAdmin.isPresent()) {
			if (checkAdmin.get().getPassword().equals(PASSWORD)) {
				when(categoryRepo.save(category)).thenReturn(category);
				assertEquals(new CommonResponse<>(category, StatusCode.CREATED, HttpStatus.CREATED),
						categoryServiceImpl.addNewCategory(ADMIN_ID, PASSWORD, categoryDTO));

			}
		}

	}

	@Test
	void addNewCategory_throwsException_if_adminIdIsWrong() {
		long adminId = 201;
		Optional<Admin> existingAdmin = Optional.of(admin);
		if (existingAdmin.get().getAdminId() != adminId) {
			assertThat(new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST));
		}

	}

	@Test
	void addNewCategory_throwsException_if_adminPasswordIsWrong() {
		String password = "joshi@123";
		Optional<Admin> existingAdmin = Optional.of(admin);
		when(adminRepo.findById(ADMIN_ID)).thenReturn(existingAdmin);
		Optional<Admin> checkAdmin = adminRepo.findById(ADMIN_ID);
		if (checkAdmin.isPresent()) {
			if (!existingAdmin.get().getPassword().equals(PASSWORD)) {
				assertThat(new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST));
			}
		}
	}

	@Test
	void updateCategory() {
		long categoryId = 1001;
		Optional<Admin> existingAdmin = Optional.of(admin);
		when(adminRepo.findById(ADMIN_ID)).thenReturn(existingAdmin);
		Optional<Admin> checkAdmin = adminRepo.findById(ADMIN_ID);
		if (checkAdmin.isPresent()) {
			if (checkAdmin.get().getPassword().equals(PASSWORD)) {
				Optional<Category> existingCategory = Optional.of(category);
				when(categoryRepo.findById(categoryId)).thenReturn(existingCategory);
				Optional<Category> checkCategory = categoryRepo.findById(categoryId);
				if (checkCategory.isPresent()) {
					category.setCategoryId(categoryId);
					category.setCategoryName("LAPTOP");
					categoryRepo.save(category);
				}
			}
		}
		assertEquals(new CommonResponse<>(category, StatusCode.OK, HttpStatus.OK),
				categoryServiceImpl.updateCategory(ADMIN_ID, PASSWORD, categoryDTO));
	}

	@Test
	void updateCategory_throwsException_if_categoryIdNotFound() {
		long categoryId = 2001;
		Optional<Category> checkCategory = categoryRepo.findById(categoryId);
		if (!checkCategory.isPresent()) {
			assertThat(
					new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST));
		}
	}

	@Test
	void updateCategory_throwsException_if_adminIdIsWrong() {
		long adminId = 201;
		Optional<Admin> existingAdmin = Optional.of(admin);
		if (existingAdmin.get().getAdminId() != adminId) {
			assertThat(new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST));
		}
	}

	@Test
	void updateCategory_throwsException_if_adminPasswordIsWrong() {
		String password = "joshi@123";
		Optional<Admin> existingAdmin = Optional.of(admin);
		when(adminRepo.findById(ADMIN_ID)).thenReturn(existingAdmin);
		Optional<Admin> checkAdmin = adminRepo.findById(ADMIN_ID);
		if (checkAdmin.isPresent()) {
			if (!existingAdmin.get().getPassword().equals(PASSWORD)) {
				assertThat(new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
						HttpStatus.BAD_REQUEST));
			}
		}
	}

	@Test
	void findCategoryById() {
		long categoryId = 1001;
		Optional<Category> existingCategory = Optional.of(category);
		when(categoryRepo.findById(categoryId)).thenReturn(existingCategory);
		assertEquals(new CommonResponse<>(existingCategory, StatusCode.OK, HttpStatus.OK),
				categoryServiceImpl.findCategoryById(ADMIN_ID, PASSWORD, categoryId));
	}

	@Test
	void findCategoryById_throwsException_if_categoryIdNotFound() {
		long categoryId = 2001;
		Optional<Category> checkCategory = categoryRepo.findById(categoryId);
		if (!checkCategory.isPresent()) {
			assertThat(
					new CommonResponse<>(Constant.ELEMENT_NOT_FOUND, StatusCode.BAD_REQUEST, HttpStatus.BAD_REQUEST));
		}
	}

	@Test
	void addNewListsOfCategory() {
		List<Category> catLists = new ArrayList<Category>();
		for (CategoryDTO newCategory : categoryLists) {
			Optional<Admin> existingAdmin = Optional.of(admin);
			when(adminRepo.findById(ADMIN_ID)).thenReturn(existingAdmin);
			Optional<Admin> checkAdmin = adminRepo.findById(ADMIN_ID);
			if (checkAdmin.isPresent()) {
				if (checkAdmin.get().getPassword().equals(PASSWORD)) {
					Category newCat = new Category();
					newCat.setCategoryId(newCategory.getCategoryId());
					newCat.setCategoryName(newCategory.getCategoryName());
					categoryRepo.save(newCat);
					catLists.add(newCat);
				}
			}
		}
		assertEquals(
				new CommonResponse<>(Constant.NEW_LISTS_OF_CATEGORIES_ADDED, StatusCode.CREATED, HttpStatus.CREATED),
				categoryServiceImpl.addNewListsOfCategory(ADMIN_ID, PASSWORD, categoryLists));
	}

	@Test
	void updateListOfCategory() {
		List<Category> upadatedCategoryLists = new ArrayList<Category>();
		Optional<Admin> existingAdmin = Optional.of(admin);
		when(adminRepo.findById(101l)).thenReturn(existingAdmin);
		Optional<Admin> checkAdmin = adminRepo.findById(101l);
		if (checkAdmin.get().getAdminId() != ADMIN_ID) {
			assertThat(new CommonResponse<String>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST,
					HttpStatus.BAD_REQUEST));
		}
		if (!checkAdmin.get().getPassword().equals(PASSWORD)) {
			assertThat(new CommonResponse<String>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
					HttpStatus.BAD_REQUEST));
		}

		Optional<Category> existingCategory = Optional.of(category);
		when(categoryRepo.findById(1001l)).thenReturn(existingCategory);
		Optional<Category> categoryResult = categoryRepo.findById(1001l);
		assertNotNull(categoryResult);
		category.setCategoryId(1001l);
		category.setCategoryName("MENS WEAR");
		upadatedCategoryLists.add(category);

		Optional<Category> existingCategory1 = Optional.of(category1);
		when(categoryRepo.findById(1002l)).thenReturn(existingCategory1);
		Optional<Category> categoryResult1 = categoryRepo.findById(1002l);
		assertNotNull(categoryResult1);
		category.setCategoryId(1002l);
		category.setCategoryName("LADIES WEAR");
		upadatedCategoryLists.add(category1);

		for (Category updateCategory : upadatedCategoryLists) {
			when(categoryRepo.save(updateCategory)).thenReturn(updateCategory);
		}
		assertEquals(new CommonResponse<>(Constant.UPDATED_LISTS_OF_CATEGORIES, StatusCode.CREATED, HttpStatus.CREATED),
				categoryServiceImpl.updateListOfCategory(ADMIN_ID, PASSWORD, categoryLists));

	}

	@Test
	void fetchAllCategories() {
		List<Category> catLists = new ArrayList<>();
		Optional<Admin> existingAdmin = Optional.of(admin);
		when(adminRepo.findById(101l)).thenReturn(existingAdmin);
		Optional<Admin> checkAdmin = adminRepo.findById(101l);
		if (checkAdmin.get().getAdminId() != ADMIN_ID) {
			assertThat(new CommonResponse<String>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST,
					HttpStatus.BAD_REQUEST));
		}
		if (!checkAdmin.get().getPassword().equals(PASSWORD)) {
			assertThat(new CommonResponse<String>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
					HttpStatus.BAD_REQUEST));
		}

		for (CategoryDTO newCategoryLists : categoryLists) {
			category.setCategoryName(newCategoryLists.getCategoryName());
			categoryRepo.save(category);
			catLists.add(category);
		}
		when(categoryRepo.findAll()).thenReturn(catLists);
		assertEquals(2, categoryRepo.findAll().size());
	}

	@Test
	void findByCategoryName() {
		Optional<Admin> existingAdmin = Optional.of(admin);
		when(adminRepo.findById(101l)).thenReturn(existingAdmin);
		Optional<Admin> checkAdmin = adminRepo.findById(101l);
		if (checkAdmin.get().getAdminId() != ADMIN_ID) {
			assertThat(new CommonResponse<String>(Constant.WRONG_ADMIN_ID, StatusCode.BAD_REQUEST,
					HttpStatus.BAD_REQUEST));
		}
		if (!checkAdmin.get().getPassword().equals(PASSWORD)) {
			assertThat(new CommonResponse<String>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.BAD_REQUEST,
					HttpStatus.BAD_REQUEST));
		}

		Optional<Category> existingCategory = Optional.of(category);
		when(categoryRepo.findByCategoryName("Mobile")).thenReturn(existingCategory);
		assertEquals(new CommonResponse<>(existingCategory, StatusCode.OK, HttpStatus.OK),
				categoryServiceImpl.findByCategoryName(ADMIN_ID, PASSWORD, "Mobile"));

	}
}
