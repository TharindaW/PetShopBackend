package com.x99me.priceEngine;

import com.x99me.priceEngine.controller.service.ProductService;
import com.x99me.priceEngine.dao.Product.ProductDAO;
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

import java.util.Arrays;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/22/2020 6:14 PM
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class PriceEngineControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	public void testProductEndpoint() throws Exception
	{

		Mockito.when( this.productService.getProduct( 1 ) ).thenReturn( new ProductDAO( 1, "Horseshoe", false ) );

		mockMvc.perform( MockMvcRequestBuilders.get( "/products/1" ) )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath( "productId" ).value( "1" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "name" ).value( "Horseshoe" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "rare" ).value( "false" ) );
	}

	@Test
	public void testAllProductsEndpoint() throws Exception
	{
		ProductDAO horseshoe = new ProductDAO( 1, "Horseshoe", false );
		ProductDAO penguin = new ProductDAO( 2, "Penguin-ears", true );


		Mockito.when( this.productService.getProducts() ).thenReturn( Arrays.asList( horseshoe, penguin ) );

		mockMvc.perform( MockMvcRequestBuilders.get( "/products" ) )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath( "[0]productId" ).value( "1" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "[0]name" ).value( "Horseshoe" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "[0]rare" ).value( "false" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "[1]productId" ).value( "2" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "[1]name" ).value( "Penguin-ears" ) )
				.andExpect( MockMvcResultMatchers.jsonPath( "[1]rare" ).value( "true" ) );
	}

}
