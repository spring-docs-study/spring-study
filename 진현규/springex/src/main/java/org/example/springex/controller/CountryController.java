package org.example.springex.controller;

import java.util.ArrayList;
import java.util.List;

import org.example.springex.dto.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

	@GetMapping("/france")
	public ResponseEntity<Country> france() {
		Country c = new Country("france",10000);
		return ResponseEntity
			.status(HttpStatus.ACCEPTED)
			.header("continent","Europe")
			.header("capital","Paris")
			.header("favorite_food","cheese and wine")
			.body(c);
	}

	@GetMapping("/all")
	public List<Country> all() {
		Country countries = Country.of("France",1000);
		Country countries2 = Country.of("Germany",2000);
		Country countries3 = Country.of("Greece",3000);

		return List.of(countries3,countries2,countries);
	}


}
