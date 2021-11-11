package com.examplespring.orderservice.api.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDTO {

	private String name;
	private int qty;
	private double amount;
	private LocalDate date;
}
