package com.examplespring.productservice.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplespring.productservice.api.entity.Product;
import com.examplespring.productservice.api.exception.ProductNotFoundException;
import com.examplespring.productservice.api.model.ProductDTO;
import com.examplespring.productservice.api.model.ProductRequestDTO;
import com.examplespring.productservice.api.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;



	/**
	 * Order API Services
	 * -------------------
	 */
	
	// 1 - Add new Product
	public String addProduct(ProductRequestDTO productRequestDTO) {
		if (productRequestDTO != null) {
			Product product = modelMapper.map(productRequestDTO, Product.class);
			productRepository.save(product);
			return "Successfully added.";
		}
		return null;
	}
	
	
	// 2 - Find all Products
	public List<ProductDTO> getProducts() {
		List<Product> products = productRepository.findAll();		
		List<ProductDTO> productDetails = products
				.stream()
				.map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
		return productDetails;
	}


	// 3 - Search a Product
	public ProductDTO searchProduct(int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return modelMapper.map(product.get(), ProductDTO.class);
		}
		return null;
	}


	// 4 - Delete a Product
	public String deleteProduct(int id) {
		if(id > 0) {
			productRepository.deleteById(id);
			return "Successfully removed.";
		}
		return null;
	}
	
	
	// 5 - Edit a Product
	public ProductDTO editProduct(int id, ProductDTO productDTO) {		
		productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found for id :: " + id));
		if (productDTO != null) {			
			Product product=modelMapper.map(productDTO, Product.class);
			productRepository.save(product);
			return productDTO;
		}
		return null;
	}
	

	// 5 - Purchase a Product
	public ProductDTO purchaseProduct(int id, int qty) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found for id :: " + id));
		int finalCount = product.getTotCount() - qty;
		
		if(finalCount > 0 && qty > 0) {
			product.setTotCount(finalCount);
			productRepository.save(product);
			System.out.println("purchased............");
			return modelMapper.map(product, ProductDTO.class);
		} else {
			throw new ProductNotFoundException("Required Item count is exhausted for id :: " + id);
		}
	}
	
	
	// 5 - Return a Product
	public ProductDTO returnProduct(int id, int qty) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found for id :: " + id));
		int finalCount = product.getTotCount() + qty;
		
		if(qty > 0) {
			product.setTotCount(finalCount);
			productRepository.save(product);
			System.out.println("returned............");
			return modelMapper.map(product, ProductDTO.class);
		} else {
			throw new ProductNotFoundException("Quatity is missing for :: " + id);
		}
	}
	

}
