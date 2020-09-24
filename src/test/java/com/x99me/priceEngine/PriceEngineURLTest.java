package com.x99me.priceEngine;

import com.x99me.priceEngine.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/22/2020 6:13 PM
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PriceEngineURLTest
{
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testProductEndpoint()
	{

		ResponseEntity<Product> product = restTemplate.getForEntity( "/products/1", Product.class );

		assertThat( product.getStatusCode() ).isEqualTo( HttpStatus.OK );

		Product body = product.getBody();
		assertThat( body ).isNotNull();
		assertThat( body.getName() ).isNotNull().isEqualTo( "Horseshoe" );
		assertThat( body.getDescription() ).isNotNull().isNotEmpty();
		assertThat( body.getProductId() ).isNotNull();
		assertThat( body.isRare() ).isNotNull();
		assertThat( body.getUnitPerCarton() ).isNotNull();
		assertThat( body.getUnitPerCarton() ).isNotNull();

	}


}
