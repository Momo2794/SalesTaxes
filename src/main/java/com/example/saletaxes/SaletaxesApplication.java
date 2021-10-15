package com.example.saletaxes;

import com.example.saletaxes.entity.Product;
import com.example.saletaxes.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class SaletaxesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaletaxesApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			ObjectMapper objectMapper = new ObjectMapper();
			TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/Product.json");

			try {
				List<Product> products = objectMapper.readValue(inputStream, typeReference);
				productService.saveAllProducts(products);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		};
	}
	 */
}
