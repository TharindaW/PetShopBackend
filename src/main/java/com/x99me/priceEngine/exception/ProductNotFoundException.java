package com.x99me.priceEngine.exception;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/24/2020 11:48 PM
 */
public class ProductNotFoundException extends RuntimeException
{
	public ProductNotFoundException( String message )
	{
		super( message );
	}
}
