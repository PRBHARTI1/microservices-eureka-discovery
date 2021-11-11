package com.examplespring.productservice.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDTO {

	private String item;
	private int totCount;
	private double price;
	private Boolean isActive;
}
