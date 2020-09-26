package com.x99me.priceEngine.controller.service;

import com.x99me.priceEngine.dao.ProductDAO;
import com.x99me.priceEngine.dto.PriceResult;
import com.x99me.priceEngine.dto.ProductForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/25/2020 11:55 PM
 */
@Service
@Slf4j
@AllArgsConstructor
public class PricingEngineService
{
	@Autowired
	private ProductService productService;

	@Deprecated
	public PriceResult calculate( ProductForm form, int qty, int productId )
	{
		ProductDAO productDAO = productService.getProduct( productId );

		log.info( productDAO.toString() );
		PriceResult priceResult = new PriceResult();
		if( ProductForm.CARTON.equals( form ) )
		{
			priceResult.setCartons( 1 );
			priceResult.setUnits( 0 );
			BigDecimal cartonPrice = productDAO.getCartonPrice().multiply( BigDecimal.valueOf( qty ) )
					.multiply( ( qty >= 3 ) ? BigDecimal.valueOf( 0.9 ) : BigDecimal.valueOf( 1 ) );

			priceResult.setPrice( cartonPrice );
		}
		else
		{
			int carton = qty / productDAO.getUnitPerCarton();
			int units = qty % productDAO.getUnitPerCarton();

			BigDecimal cartonPrice = productDAO.getCartonPrice().multiply( BigDecimal.valueOf( carton ) )
					.multiply( (carton >=3) ? BigDecimal.valueOf( 0.9 ) : BigDecimal.valueOf( 1 ) );
			BigDecimal unitPrice = BigDecimal.valueOf( units ).multiply(
					productDAO.getCartonPrice().divide( BigDecimal.valueOf( productDAO.getUnitPerCarton() ), 2, RoundingMode.HALF_EVEN )
			).multiply( BigDecimal.valueOf( 1.3 ) );

			priceResult.setCartons( carton );
			priceResult.setUnits( units );
			priceResult.setPrice( unitPrice.add( cartonPrice ) );

			System.out.println();
		}

		log.info( "Request reached to the service" );
		return priceResult;
	}

	public PriceResult calculate( int carton , int unit, int productId )
	{
		ProductDAO productDAO = productService.getProduct( productId );

		log.info( productDAO.toString() );
		PriceResult priceResult = new PriceResult();

		System.out.println( "Req : carton = " + carton + " unit = " + unit );
		int newCarton = carton +  unit / productDAO.getUnitPerCarton();
		int newUnit = unit % productDAO.getUnitPerCarton();

		System.out.println( "New : carton = " + newCarton + " unit = " + newUnit );

		priceResult.setCartons( newCarton );
		priceResult.setUnits( newUnit );

		BigDecimal discountFactor = ( newCarton >= 3 ) ? BigDecimal.valueOf( 0.9 ) : BigDecimal.valueOf( 1 );

		BigDecimal cartonPrice = productDAO.getCartonPrice().multiply( BigDecimal.valueOf( newCarton ) )
				.multiply( discountFactor );

		System.out.println( "Carton Price : " + cartonPrice + " | x " + discountFactor );
		BigDecimal unitPrice = BigDecimal.valueOf( newUnit ).multiply(
				productDAO.getCartonPrice().divide( BigDecimal.valueOf( productDAO.getUnitPerCarton() ), 2, RoundingMode.HALF_EVEN )
		).multiply( BigDecimal.valueOf( 1.3 ) );

		System.out.println( "Unit Price : " + unitPrice );

		priceResult.setPrice( unitPrice.add( cartonPrice ) );

		log.info( "Request reached to the service" );
		return priceResult;
	}


}
