package com.xoriant.shop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryId;

	@Pattern(regexp = "[A-Za-z]*")
	@Size(min = 1, max = 15, message = "atleast enter one character")
	private String categoryName;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")
//	private List<Brand> brandLists;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")
//	private List<Product> productLists;

}
