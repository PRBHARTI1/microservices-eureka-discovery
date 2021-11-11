package com.examplespring.productservice.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerUIConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(RequestHandlerSelectors.basePackage("com.examplespring.productservice.api.controller"))
				.paths(PathSelectors.any())
				.build();
	}


	private ApiInfo getApiInfo() {
		Contact contact = new Contact("Priyadarshini RB", "http://productservice.examplespring.com", "priyarb1@gmail.com");
		return new ApiInfoBuilder()
				.title("Springboot Product API")
				.description("Springboot Microservices API demonstrations")
				.version("0.0.1-SNAPSHOT")
				.license("NA")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
	}

}
