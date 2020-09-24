package com.x99me.priceEngine.service;

import com.x99me.priceEngine.controller.service.ProductService;
import com.x99me.priceEngine.dao.ProductDAO;
import com.x99me.priceEngine.exception.ProductNotFoundException;
import com.x99me.priceEngine.repo.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/24/2020 11:25 PM
 */

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest
{
	@Mock
	private ProductRepository productRepository;

	private ProductService productService;

	@Before
	public void before()
	{
		productService = new ProductService( productRepository );
	}

	@Test
	public void testGetProduct()
	{
		Mockito.when( this.productRepository.findById( 1 ) ).thenReturn( new ProductDAO( 1, "Horseshoe", false ) );

		ProductDAO product = this.productService.getProduct( 1 );

		Assert.assertEquals( product.getProductId(), 1 );
		Assert.assertEquals( product.getName(), "Horseshoe" );
		Assert.assertFalse( product.isRare() );
	}

	@Test(expected = ProductNotFoundException.class)
	public void testProductNotFound()
	{
		Mockito.when( this.productRepository.findById( 1 ) ).thenReturn( null );
		productService.getProduct( 1 );
	}
}
