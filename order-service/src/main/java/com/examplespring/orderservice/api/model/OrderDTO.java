package com.examplespring.orderservice.api.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class OrderDTO {

	private Integer id;

	@NotEmpty
	private String itemName;

	private String description;

	@NotNull
	private int quantity;

	private double totalPrice;

	private LocalDate date;

	@NotEmpty
	@Email(message="${validatedValue} is not a valid email")
	private String email;
}
