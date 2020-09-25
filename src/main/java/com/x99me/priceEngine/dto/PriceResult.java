package com.x99me.priceEngine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/26/2020 12:06 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceResult
{
	private BigDecimal price;
	private int cartons;
	private int units;
	private String resultDetails;
}
