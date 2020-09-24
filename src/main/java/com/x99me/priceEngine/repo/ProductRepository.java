package com.x99me.priceEngine.repo;

import com.x99me.priceEngine.dao.ProductDAO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/24/2020 11:27 PM
 */

public interface ProductRepository extends CrudRepository<ProductDAO, Integer>
{
	ProductDAO findById( int id );
}
