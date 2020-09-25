package com.x99me.priceEngine.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/22/2020 9:20 PM
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductDAO
{
	@Id
	@Column(name = "product_id")
	private int productId;
	private String name;
	private String description;
	private boolean rare; // Rare product or not

	@Column(name = "unit_per_carton")
	private Short unitPerCarton;

	@Column(name = "carton_price")
	private BigDecimal cartonPrice;

	@Column(name = "img_url")
	private String imageURL;


	public ProductDAO( int productId, String name, boolean rare )
	{
		this.productId = productId;
		this.name = name;
		this.rare = rare;
	}
}
