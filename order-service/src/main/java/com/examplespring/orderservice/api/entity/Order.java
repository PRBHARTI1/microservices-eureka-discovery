package com.examplespring.orderservice.api.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor	
@Entity
@Table(name = "`order_detail`")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String itemName;
	private String description;
	private int quantity;
	private double totalPrice;
	private LocalDate date;
	private String email;
}
