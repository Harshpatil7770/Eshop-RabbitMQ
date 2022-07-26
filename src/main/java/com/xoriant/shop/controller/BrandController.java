package com.xoriant.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/save/{adminId}/{password}")
	public CommonResponse<?> addNewBrand(@Valid @PathVariable Long adminId, @PathVariable String password,
			@RequestBody BrandDTO brandDTO) {
		return brandService.addNewBrand(adminId, password, brandDTO);
	}
	
	
}
