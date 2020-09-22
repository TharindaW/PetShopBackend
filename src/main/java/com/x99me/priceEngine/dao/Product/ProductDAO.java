package com.x99me.priceEngine.dao.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/22/2020 9:20 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDAO
{
	private int productId;
	private String name;
	private String description;
	private boolean rare; // Rare product or not

	private Short unitPerCarton;
	private BigDecimal cartonPrice;


	public ProductDAO( int productId, String name, boolean rare )
	{
		this.productId = productId;
		this.name = name;
		this.rare = rare;
	}
}
