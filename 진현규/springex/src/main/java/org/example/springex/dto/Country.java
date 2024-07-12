package org.example.springex.dto;

public class Country {
	private String name;
	private int population;

	public Country () {
	}

	public Country (String name, int population) {
		this.name = name;
		this.population = population;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getPopulation () {
		return population;
	}

	public void setPopulation (int population) {
		this.population = population;
	}

	public static Country of(final String name, final int population) {
		Country country = new Country();
		country.setName(name);
		country.setPopulation(population);
		return country;
	}
}
