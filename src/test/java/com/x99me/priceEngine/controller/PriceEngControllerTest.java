package com.x99me.priceEngine.controller;

import com.x99me.priceEngine.controller.service.PricingEngineService;
import com.x99me.priceEngine.dto.PriceResult;
import com.x99me.priceEngine.dto.ProductForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/25/2020 11:53 PM
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PriceEngineController.class)
public class PriceEngControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PricingEngineService pricingEngineService;

	@Test
	public void testDoPriceEndpoint() throws Exception
	{

		Mockito.when( this.pricingEngineService.calculate( ProductForm.CARTON, 2, 1 ) )
				.thenReturn( new PriceResult( BigDecimal.valueOf( 825.00 ), 1, 0, "No Discount" ) );

		mockMvc.perform( MockMvcRequestBuilders.get( "/calculate?productForm=CARTON&qty=2&productId=1" ) )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath( "price" ).value( "825.0" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "cartons" ).value( "1" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "units" ).value( "0" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "resultDetails" ).value( "No Discount" ) );
	}
}
