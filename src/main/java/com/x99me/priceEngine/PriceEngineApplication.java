package com.x99me.priceEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PriceEngineApplication
{
	public static void main( String[] args )
	{
		SpringApplication.run( PriceEngineApplication.class, args );
	}

}
