package com.xoriant.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;

	@Pattern(regexp = "^[a-zA-Z ]*$")
	@Size(min = 1, max = 15, message = "atleast enter one character")
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z ]*$")
	@Size(min = 1, max = 15, message = "atleast enter one character")
	private String lastName;

	//@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	//@Size(message = "Enter Valid Mail")
	private String emailId;

	private long mobNo;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "username")
	@Size(min = 1, max = 15, message = "atleast enter one character")
	private String userName;

	@Size(min = 1, max = 15, message = "atleast enter one character")
	private String password;

}
