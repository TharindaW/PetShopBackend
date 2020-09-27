package com.x99me.priceEngine.service;

import com.x99me.priceEngine.controller.service.PricingEngineService;
import com.x99me.priceEngine.controller.service.ProductService;
import com.x99me.priceEngine.dao.ProductDAO;
import com.x99me.priceEngine.dto.PriceResult;
import com.x99me.priceEngine.dto.ProductForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	@Deprecated
	public void testCalculate()
	{
		ProductDAO productDAO = new ProductDAO( 1, "Horseshoe", "", false, (short) 10, BigDecimal.valueOf( 175 ), "" );
		Mockito.when( this.productService.getProduct( 1 ) ).thenReturn( productDAO );

		PriceResult priceResult = this.pricingEngineService.calculate( ProductForm.CARTON, 2, 1 );

		Assert.assertEquals( priceResult.getPrice(), BigDecimal.valueOf( 350 ) );

	}

	@Test
	public void testPriceCalculation() throws Exception
	{
		ProductDAO productDAO = new ProductDAO( 1, "Horseshoe", false );
		productDAO.setCartonPrice( BigDecimal.valueOf( 175 ) );
		productDAO.setUnitPerCarton( (short) 5 );

		Mockito.when( this.productService.getProduct( 1 ) ).thenReturn( productDAO );


		PriceResult priceResult = null;

		priceResult = new PriceResult( BigDecimal.valueOf( 0 ), 0, 0, "" ); // Test zero
		evalAndAssert( 0, 0, priceResult );

		priceResult = new PriceResult( BigDecimal.valueOf( 175 ), 1, 0, "" ); // Test single carton
		evalAndAssert( 1, 0, priceResult );

		priceResult = new PriceResult( BigDecimal.valueOf( 45.5 ), 0, 1, "" ); // Test unit calculation *1.3
		evalAndAssert( 0, 1, priceResult );

		priceResult = new PriceResult( BigDecimal.valueOf( 136.5 ), 0, 3, "" ); // Test unit calculation *1.3 and multiplication
		evalAndAssert( 0, 3, priceResult );

		priceResult = new PriceResult( BigDecimal.valueOf( 350 ), 2, 0, "" ); // Test carton calculation and multiplication without discount
		evalAndAssert( 2, 0, priceResult );

		priceResult = new PriceResult( BigDecimal.valueOf( 472.5 ), 3, 0, "" ); // Test carton calculation and multiplication with discount
		evalAndAssert( 3, 0, priceResult );

		priceResult = new PriceResult( BigDecimal.valueOf( 472.5 + 182 ), 3, 4, "" ); // Test carton + unit calculation
		evalAndAssert( 3, 4, priceResult );

		priceResult = new PriceResult( BigDecimal.valueOf( 630 + 91 ), 4, 2, "" ); // price optimization
		evalAndAssert( 3, 7, priceResult );

	}

	private void evalAndAssert( int reqCarton, int reqUnit, PriceResult expectedResult ) throws Exception
	{
		BigDecimal expected = expectedResult.getPrice().setScale( 2, RoundingMode.HALF_EVEN );
		PriceResult priceResult = this.pricingEngineService.calculate( reqCarton, reqUnit, 1 );

		Assert.assertEquals( priceResult.getPrice(), expected );
		Assert.assertEquals( priceResult.getCartons(), expectedResult.getCartons() );
		Assert.assertEquals( priceResult.getUnits(), expectedResult.getUnits() );
	}
}
