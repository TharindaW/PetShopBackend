package com.x99me.priceEngine.controller;

import com.x99me.priceEngine.controller.service.ProductService;
import com.x99me.priceEngine.dao.Product.ProductDAO;
import com.x99me.priceEngine.dto.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/22/2020 9:15 PM
 */
@RestController
public class ProductController
{
	@Autowired
	private ProductService productService;

	@GetMapping("/products/{id}")
	public Product getProduct( @PathVariable int id )
	{
		ProductDAO productDAO = productService.getProduct( id );
		return productMapper( productDAO );
	}

	@GetMapping("/products")
	public List<Product> getProduct()
	{
		List<ProductDAO> products = productService.getProducts();
		return products.stream().map( this::productMapper ).collect( Collectors.toList() );
	}

	private Product productMapper( ProductDAO productDAO )
	{
		Product product = new Product();
		product.setProductId( productDAO.getProductId() );
		product.setName( productDAO.getName() );
		product.setDescription( productDAO.getDescription() );
		product.setRare( productDAO.isRare() );

		return product;
	}


}
