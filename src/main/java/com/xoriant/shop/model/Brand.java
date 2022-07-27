package com.xoriant.shop.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(fetch = FetchType.EAGER)
	private long brandId;

	@Pattern(regexp = "^[a-zA-Z ]*$")
	@Size(min = 1, max = 15, message = "Please enter atleast one character!")
	private String brandName;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "brand_id", referencedColumnName = "brandId")
//	private List<Product> productLists;
}
