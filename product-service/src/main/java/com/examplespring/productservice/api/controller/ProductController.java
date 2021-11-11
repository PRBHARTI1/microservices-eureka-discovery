package com.examplespring.productservice.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.examplespring.productservice.api.model.ProductDTO;
import com.examplespring.productservice.api.model.ProductRequestDTO;
import com.examplespring.productservice.api.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;


	/**
	 * Product API calls
	 * ---------------
	 */
	
	// 1 - Add new Product
	@PostMapping(value = "/add", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<String> addProduct(@RequestBody ProductRequestDTO requestDTO) {
		String response = productService.addProduct(requestDTO);
		
		if(response != null) {
			return new ResponseEntity<String>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 2 - Find all Products
	@GetMapping(value = "/list")
	public ResponseEntity<List<ProductDTO>> getProducts() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}
		
		
	// 3 - Search a Product
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<ProductDTO> searchProduct(@PathVariable("id") int id) {
		ProductDTO productDTO = productService.searchProduct(id);
		
		if(productDTO != null) {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.BAD_REQUEST);
		}
	}

	
	// 4 - Delete a Product
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		String response = productService.deleteProduct(id);
		
		if(response != null) {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 5 - Edit a Product
	@PutMapping(path = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> editProduct(@PathVariable(value = "id") int id, @RequestBody ProductDTO productDTO) {
		ProductDTO newProductDTO = productService.editProduct(id, productDTO);
		
		if(newProductDTO != null) {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);			
		} else {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 5 - Purchase a Product
	//@PostMapping("/purchase/{id}?qty={qty}")
	@PostMapping("/purchase/{id}")
	public ResponseEntity<ProductDTO> purchaseProduct(@PathVariable(value = "id") int id, @RequestParam("qty") int qty) {
		System.out.println("ID::"+id+" , QTY::"+qty);
		ProductDTO productDTO = productService.purchaseProduct(id, qty);
		
		if(productDTO != null) {	
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 5 - Return a Product
	@PostMapping("/return/{id}")
	public ResponseEntity<ProductDTO> returnProduct(@PathVariable(value = "id") int id, @RequestParam("qty") int qty) {
		System.out.println("ID::"+id+" , QTY::"+qty);
		ProductDTO productDTO = productService.returnProduct(id, qty);
		
		if(productDTO != null) {	
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.BAD_REQUEST);
		}
	}
}
