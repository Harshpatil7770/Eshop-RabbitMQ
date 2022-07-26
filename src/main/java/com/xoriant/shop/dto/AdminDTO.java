package com.xoriant.shop.dto;

import com.xoriant.shop.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

	private long adminId;

	private String firstName;

	private String lastName;

	private String emailId;

	private long mobNo;

	private Gender gender;

	private String userName;

	private String password;
}
