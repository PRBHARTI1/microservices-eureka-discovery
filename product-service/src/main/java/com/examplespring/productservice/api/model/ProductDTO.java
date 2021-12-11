package com.examplespring.productservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Integer id;
	private String itemName;
	private int totCount;
	private double itemRate;
	private Boolean isActive;
}
