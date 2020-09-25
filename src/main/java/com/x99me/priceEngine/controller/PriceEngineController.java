package com.x99me.priceEngine.controller;

import com.x99me.priceEngine.controller.service.PricingEngineService;
import com.x99me.priceEngine.dto.PriceResult;
import com.x99me.priceEngine.dto.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tharinda Wickramaarachchi
 * @since 9/26/2020 12:21 AM
 */
@RestController
public class PriceEngineController
{
	@Autowired
	private PricingEngineService pricingEngineService;

	@GetMapping("/calculate")
	private ResponseEntity<PriceResult> calculate( @RequestParam(required = true) ProductForm productForm,
												   @RequestParam(required = true) int qty,
												   @RequestParam(required = true) int productId )
	{
		PriceResult calculate = pricingEngineService.calculate( productForm, qty, productId );

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add( "Access-Control-Allow-Origin", "http://localhost:4200" );

		return ResponseEntity.ok()
				.headers( httpHeaders )
				.body( calculate );

	}
}
