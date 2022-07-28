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
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Pattern(regexp = "^[a-zA-Z ]*$")
	@Size(min = 1, max = 15, message = "atleast enter one character")
	private String userFullName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "username")
	private String userName;

	private String password;

}
