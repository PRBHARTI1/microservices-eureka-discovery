package com.examplespring.orderservice.api;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

@SpringBootApplication
@EnableEurekaClient
//public class OrderServiceApplication extends PatternLayoutEncoder implements CommandLineRunner {
public class OrderServiceApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceApplication.class);
	
	@Autowired
    private Environment env;
	
	public OrderServiceApplication() {
		
	}
	
	@Override
    public void run(String... args) throws Exception {

        logger.info("{}", env.getProperty("JAVA_HOME"));
        logger.info("{}", env.getProperty("app.name"));
		logger.info("{}", System.getProperty ("java.io.tmpdir"));
		logger.info("{}", System.getProperty("java.class.path"));
        
        logger.warn("warning msg.....................................");
        logger.error("error msg......................................");
        logger.trace("trace msg......................................");
    }

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@Bean
	public ModelMapper map() {
		return new ModelMapper();
	}
}
