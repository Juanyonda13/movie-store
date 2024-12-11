package com.movies_shop.movies_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.movies_shop.movies_shop.model.dao")
@EntityScan(basePackages = "com.movies_shop.movies_shop.model.entity")
public class MoviesShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesShopApplication.class, args);
	}

}
