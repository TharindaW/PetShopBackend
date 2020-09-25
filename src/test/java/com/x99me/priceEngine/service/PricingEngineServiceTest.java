package com.x99me.priceEngine.service;

import com.x99me.priceEngine.controller.service.PricingEngineService;
import com.x99me.priceEngine.controller.service.ProductService;
import com.x99me.priceEngine.dao.ProductDAO;
import com.x99me.priceEngine.dto.PriceResult;
import com.x99me.priceEngine.dto.ProductForm;
import com.x99me.priceEngine.repo.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/26/2020 1:06 AM
 */
@RunWith(MockitoJUnitRunner.class)
public class PricingEngineServiceTest
{
	@Mock
	private ProductService productService;

	private PricingEngineService pricingEngineService;

	@Before
	public void before()
	{
		pricingEngineService = new PricingEngineService( productService );
	}

	@Test
	public void testCalculate()
	{
		ProductDAO productDAO = new ProductDAO( 1, "Horseshoe", "", false, (short) 10, BigDecimal.valueOf( 175 ) , "");
		Mockito.when( this.productService.getProduct( 1 ) ).thenReturn( productDAO );

		PriceResult priceResult = this.pricingEngineService.calculate( ProductForm.CARTON, 2, 1 );

		Assert.assertEquals( priceResult.getPrice(), BigDecimal.valueOf( 350 ) );

	}
}
