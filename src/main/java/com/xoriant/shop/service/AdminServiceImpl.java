package com.xoriant.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xoriant.shop.dao.AdminRepo;
import com.xoriant.shop.dto.AdminDTO;
import com.xoriant.shop.model.Admin;
import com.xoriant.shop.utility.CommonResponse;
import com.xoriant.shop.utility.Constant;
import com.xoriant.shop.utility.StatusCode;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public CommonResponse<?> createAdmin(AdminDTO adminDTO) {
		try {
			Admin admin = new Admin();
			admin.setFirstName(adminDTO.getFirstName());
			admin.setLastName(adminDTO.getLastName());
			admin.setEmailId(adminDTO.getEmailId());
			admin.setMobNo(adminDTO.getMobNo());
			admin.setGender(adminDTO.getGender());
			admin.setUserName(adminDTO.getUserName());
			admin.setPassword(adminDTO.getPassword());
			adminRepo.save(admin);
			return new CommonResponse<>(admin, StatusCode.CREATED, HttpStatus.CREATED);

		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CommonResponse<?> changePassword(AdminDTO adminDTO, Long adminId, String oldPassword) {
		try {
			Optional<Admin> existingAdmin = adminRepo.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			if (!existingAdmin.get().getPassword().equals(oldPassword)) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_PASSWORD, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			Admin updateAdminDetails = adminRepo.findById(adminId).orElse(null);
			if (updateAdminDetails == null) {
				return new CommonResponse<>(Constant.WRONG_ADMIN_ID, StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);
			}

			updateAdminDetails.setPassword(adminDTO.getPassword());
			adminRepo.save(updateAdminDetails);
			return new CommonResponse<String>(Constant.PASSWORD_CHANGED, StatusCode.OK, HttpStatus.OK);
		} catch (Exception e) {
			return new CommonResponse<>(e.getMessage(), StatusCode.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
