package com.xoriant.shop.service;

import com.xoriant.shop.dto.AdminDTO;
import com.xoriant.shop.dto.UserDTO;
import com.xoriant.shop.utility.CommonResponse;

public interface AdminService {

	CommonResponse<?> createAdmin(AdminDTO adminDTO);

	CommonResponse<?> changePassword(AdminDTO adminDTO, Long adminId, String oldPassword);
	
	CommonResponse<?> createUserAccount(long  adminId,String password,UserDTO userDTO);
	
	CommonResponse<?> updateUserAccount(long  adminId,String password,UserDTO userDTO);
}
