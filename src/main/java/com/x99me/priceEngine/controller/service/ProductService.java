package com.x99me.priceEngine.controller.service;

import com.x99me.priceEngine.dao.ProductDAO;
import com.x99me.priceEngine.exception.ProductNotFoundException;
import com.x99me.priceEngine.repo.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/22/2020 9:15 PM
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProductService
{

	private ProductRepository productRepository;

	public ProductDAO getProduct( int id )
	{
		ProductDAO productDAO = productRepository.findById( id );

		if( productDAO == null )
		{
			ProductNotFoundException productNotFoundException = new ProductNotFoundException( "Product ID " + id + " not found" );
			log.error( "Product ID " + id + " not found", productNotFoundException );
			throw productNotFoundException;
		}

		return productDAO;
	}

	public List<ProductDAO> getProducts()
	{
		List<ProductDAO> productDAOS = new ArrayList<>();
		productRepository.findAll().forEach( productDAOS::add );

		return productDAOS.stream().sorted(Comparator.comparing( ProductDAO::getProductId ) ).collect( Collectors.toList());

	}
}
