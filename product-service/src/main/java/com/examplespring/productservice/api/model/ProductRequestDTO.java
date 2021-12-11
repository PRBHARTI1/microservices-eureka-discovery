package com.examplespring.productservice.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDTO {

	private String itemName;
	private int totCount;
	private double itemRate;
	private Boolean isActive;
}
