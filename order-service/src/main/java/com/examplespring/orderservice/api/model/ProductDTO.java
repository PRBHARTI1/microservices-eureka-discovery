package com.examplespring.orderservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Integer id;

	@NotEmpty
	private String itemName;

	private int totCount;

	private double itemRate;

	private Boolean isActive;
}
