package com.x99me.priceEngine.repo;

import com.x99me.priceEngine.dao.ProductDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/25/2020 12:00 AM
 */
@RunWith(SpringRunner.class)
@DataJpaTest(properties = {"classpath:application.properties"})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest
{
	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testGetProduct()
	{
		ProductDAO horseshoe = new ProductDAO( 1, "Horseshoe", false );
		ProductDAO savedProductDAO = productRepository.save( new ProductDAO( 1, "Horseshoe", false ) );

		Assert.assertEquals( horseshoe.getProductId() , savedProductDAO.getProductId() );
		Assert.assertEquals( horseshoe.getName() , savedProductDAO.getName() );
		Assert.assertEquals( horseshoe.isRare() , savedProductDAO.isRare() );
	}

}
