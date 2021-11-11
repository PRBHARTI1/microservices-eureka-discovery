package com.examplespring.orderservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examplespring.orderservice.api.model.OrderDTO;
import com.examplespring.orderservice.api.model.OrderRequestDTO;
import com.examplespring.orderservice.api.model.ProductDTO;
import com.examplespring.orderservice.api.service.OrderService;

//@CrossOrigin(origins="*")
@RestController
@RequestMapping("/orders")
//@Api(tags = "Order Controller", value = "OrderController")
public class OrderController {

	@Autowired
	private OrderService orderService;


	/**
	 * Order API calls
	 * ---------------
	 */
	
	// 1 - Place new Order
	@PostMapping(value = "/create", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO requestDTO) {
		String response = orderService.createOrder(requestDTO);
		if(response != null) {
			return new ResponseEntity<String>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}

	
	// 2 - Find all Orders
	@GetMapping(value = "/list")
	public ResponseEntity<List<OrderDTO>> getOrders() {
		return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
	}
	

	// 3 - Search an Order
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<OrderDTO> searchOrder(@PathVariable("id") int id) {
		OrderDTO orderDTO = orderService.searchOrder(id);			
		if(orderDTO != null) {
			return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.BAD_REQUEST);
		}		
	}
	
	
	// 4 - Delete an Order
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable int id) {
		String response = orderService.deleteOrder(id);
		if(response != null) {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 5 - Edit an Order
	//@PutMapping(path = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(path = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> editOrder(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
		String response = orderService.editOrder(id, orderDTO);
		if(response != null) {
			return new ResponseEntity<String>(response, HttpStatus.OK);			
		} else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	/**
	 * Product API Services
	 * ---------------------
	 */
	
	// 1 - Search a Product
	@GetMapping(value = "/searchProduct/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") int id) {		
		ProductDTO productDTO = orderService.searchProduct(id);		
		if(productDTO != null) {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.BAD_REQUEST);
		}		
	}
	
	
	// 2 - Purchase a Product
	@GetMapping(value = "/purchaseProduct/{id}")
	public ResponseEntity<ProductDTO> purchaseProduct(@PathVariable(value = "id") int id, @RequestParam("qty") int qty) {
		ProductDTO productDTO = orderService.purchaseProduct(id, qty);
		if(productDTO != null) {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	// 3 - Return a Product
	@GetMapping(value = "/returnProduct/{id}")
	public ResponseEntity<ProductDTO> returnProduct(@PathVariable int id, @RequestParam int qty) {
		ProductDTO productDTO = orderService.returnProduct(id, qty);		
		if(productDTO!=null) {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.BAD_REQUEST);
		}
	}

}
