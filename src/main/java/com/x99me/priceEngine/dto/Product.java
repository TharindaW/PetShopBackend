package com.x99me.priceEngine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/22/2020 6:30 PM
 */
@Data
@NoArgsConstructor
@Component
public class Product
{
	private int productId;
	private String name;
	private String description;
	private boolean rare; // Rare product or not

	private Short unitPerCarton;
	private BigDecimal cartonPrice;
	private String img;
}
