package com.examplespring.productservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Integer id;
	private String item;
	private int totCount;
	private double price;
	private Boolean isActive;
}
