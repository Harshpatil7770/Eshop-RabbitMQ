package com.xoriant.shop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.shop.dto.BrandDTO;
import com.xoriant.shop.service.BrandService;
import com.xoriant.shop.utility.CommonResponse;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@PostMapping("/{adminId}/{password}/save")
	public CommonResponse<?> addNewBrand(@Valid @PathVariable Long adminId, @PathVariable String password,
			@RequestBody BrandDTO brandDTO) {
		return brandService.addNewBrand(adminId, password, brandDTO);
	}

	@PutMapping("/{adminId}/{password}/update")
	public CommonResponse<?> updateBrand(@PathVariable Long adminId, @PathVariable String password,
			@RequestBody BrandDTO brandDTO) {
		return brandService.updateBrand(adminId, password, brandDTO);
	}

	@GetMapping("{adminId}/{password}/find/{brandId}")
	public CommonResponse<?> findBrandById(@PathVariable Long adminId, @PathVariable String password,
			@PathVariable long brandId) {
		return brandService.findBrandById(adminId, password, brandId);
	}

	@PostMapping("/{adminId}/{password}/saveAll")
	public CommonResponse<?> addNewListsOfBrands(@PathVariable Long adminId, @PathVariable String password,
			@RequestBody List<BrandDTO> brandDTO) {
		return brandService.addNewListsOfBrands(adminId, password, brandDTO);
	}

	@PutMapping("/{adminId}/{password}/updateAll")
	public CommonResponse<?> updateListOfBrand(@PathVariable Long adminId, @PathVariable String password,
			@RequestBody List<BrandDTO> brandDTO) {
		return brandService.updateListOfBrand(adminId, password, brandDTO);
	}

	@GetMapping("/{adminId}/{password}/findAll")
	public CommonResponse<?> fetchAllBrands(@PathVariable Long adminId, @PathVariable String password) {
		return brandService.fetchAllBrands(adminId, password);
	}

	@GetMapping("/{adminId}/{password}/find-brand/{brandName}")
	public CommonResponse<?> findBrandBrandName(@PathVariable Long adminId, @PathVariable String password,
			@PathVariable String brandName) {
		return brandService.findBrandBrandName(adminId, password, brandName);
	}

	@GetMapping("/{adminId}/{password}/findAll/alphabeticalOrder")
	public CommonResponse<?> findAllBrandAlphabeticalOrder(@PathVariable Long adminId, @PathVariable String password) {
		return brandService.findAllBrandAlphabeticalOrder(adminId, password);
	}

	@GetMapping("/{adminId}/{password}/findAll-brands/{firstLetter}")
	public CommonResponse<?> findAllBrandBasedOnFirstLetter(@PathVariable Long adminId, @PathVariable String password,
			@PathVariable String firstLetter) {
		return brandService.findAllBrandBasedOnFirstLetter(adminId, password, firstLetter);
	}
}
