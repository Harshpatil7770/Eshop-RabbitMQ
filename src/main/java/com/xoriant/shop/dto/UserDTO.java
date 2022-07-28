package com.xoriant.shop.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.xoriant.shop.model.Gender;
import com.xoriant.shop.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long userId;

	private String userFullName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "username")
	private String userName;

	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(columnDefinition = "order_id", referencedColumnName = "userId")
	private List<Order> order;
}
