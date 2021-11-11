package com.examplespring.orderservice.api.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.examplespring.orderservice.api.entity.Order;
import com.examplespring.orderservice.api.model.OrderDTO;
import com.examplespring.orderservice.api.model.OrderRequestDTO;
import com.examplespring.orderservice.api.model.ProductDTO;
import com.examplespring.orderservice.api.repository.OrderRepository;


@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate rest;
	
	//@Value("$product.url.locator")
	private final String PRODUCT_URL;	
	
	@Autowired
	public OrderService(@Value("${product.url.locator}") String productUrl) {
		this.PRODUCT_URL=productUrl;
	}
	
	/*
	 * private static final String URL_GET =
	 * "http://localhost:9091/product/get/{id}"; private static final String
	 * URL_UPDATE = "http://localhost:9091/product/update/{id}"; private static
	 * final String URL_ORDER = "http://localhost:9091/product/orderProduct/{id}";
	 */
	

	/**
	 * Order API Services
	 * -------------------
	 */
	
	// 1 - Place new Order
	public String createOrder(OrderRequestDTO requestDTO) {		
		if (requestDTO != null) {			
			Order order = modelMapper.map(requestDTO, Order.class);
			order.setDate(LocalDate.now());
			orderRepository.save(order);
			return "Successfully created.";
		}
		return null;
	}
	
	
	// 2 - Find all Orders
	public List<OrderDTO> getOrders() {
		List<Order> orders=orderRepository.findAll();		
		List<OrderDTO> orderDetails = orders
				.stream()
				.map(order -> modelMapper.map(order, OrderDTO.class))
				.collect(Collectors.toList());
		return orderDetails;
	}
	
	
	// 3 - Search an Order
	public OrderDTO searchOrder(int id) {
		Optional<Order> order = orderRepository.findById(id);
		if(order.isPresent()) {
			return modelMapper.map(order.get(), OrderDTO.class);
		}
		return null;
	}
	
	
	// 4 - Delete an Order
	public String deleteOrder(int id) {
		if(id>0) {
			orderRepository.deleteById(id);
			return "Successfully removed.";
		}
		return null;
	}
	
	
	// 5 - Edit an Order
	public String editOrder(int id, OrderDTO orderDTO) {		
		Optional<Order> searchOrder = orderRepository.findById(id);
		if(searchOrder.isPresent()) {
			if (orderDTO != null) {			
				Order order=modelMapper.map(orderDTO, Order.class);
				order.setDate(LocalDate.now());
				orderRepository.save(order);
				return "Successfully edited.";
			}
		}
		return null;
	}

	

	/**
	 * Product API Services
	 * ---------------------
	 */
	
	// 1 - Search a Product
	public ProductDTO searchProduct(int id) {
		//rest.getForObject(url, responseType)
		String URL = PRODUCT_URL + "/products/search/{id}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<ProductDTO> productDetail = rest.exchange(URL,  
				HttpMethod.GET,
				entity,
				ProductDTO.class,
				id);
		
		return productDetail.getBody();
	}


	// 2 - Purchase a Product
	public ProductDTO purchaseProduct(int id, int qty) {
		String URL = PRODUCT_URL + "/products/purchase/{id}?qty={qty}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<ProductDTO> productDetail = null;
		
		try {
			productDetail = rest.exchange(URL,  
					HttpMethod.POST,
					entity,
					ProductDTO.class,
					id,
					qty);
		
		} catch(Exception ex) {
			System.out.println("Exception in purchaseProduct ::::: "+ex.getMessage());
			ex.printStackTrace();
		}
		
		
		return productDetail.getBody();
	}
	
	
	// 3 - Return a Product
	public ProductDTO returnProduct(int id, int qty) {
		String URL = PRODUCT_URL + "/products/return/{id}?qty={qty}";
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("qty", qty);
		// create URIBuilder and test
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<ProductDTO> productDetail = null;
		
		try {
			productDetail = rest.exchange(URL,  
					HttpMethod.POST,
					entity,
					ProductDTO.class,
					id,
					qty);
			
		} catch(Exception ex) {
			System.out.println("Exception in returnProduct ::::: "+ex.getMessage());
			ex.printStackTrace();
		}
		
		return productDetail.getBody();
	}
	
	
}
