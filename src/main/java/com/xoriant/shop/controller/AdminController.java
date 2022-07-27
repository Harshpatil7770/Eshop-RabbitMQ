package com.xoriant.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.shop.dto.AdminDTO;
import com.xoriant.shop.model.Admin;
import com.xoriant.shop.msgsender.MessageSender;
import com.xoriant.shop.service.AdminService;
import com.xoriant.shop.utility.CommonResponse;
import com.xoriant.shop.utility.StatusCode;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private MessageSender messageSender;

	@PostMapping("/save")
	public CommonResponse<?> createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
		return adminService.createAdmin(adminDTO);
	}

	@PatchMapping("/update/{adminId}/{oldPassword}")
	public CommonResponse<?> changePassword(@RequestBody AdminDTO adminDTO, @PathVariable Long adminId,
			@PathVariable String oldPassword) {
		CommonResponse<?> result = adminService.changePassword(adminDTO, adminId, oldPassword);
		if (result.getStatusCode() == StatusCode.OK) {
			String response = "Password Changed Succesfully !" + "\n" + "Your new Password is :"
					+ adminDTO.getPassword();
			messageSender.changePassword(response);
		}
		return result;
	}
}
