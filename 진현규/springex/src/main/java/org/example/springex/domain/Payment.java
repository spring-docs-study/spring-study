package org.example.springex.domain;

public class Payment {
	private String id;
	private Double amount;

	public Payment (String id, Double amount) {
		this.id = id;
		this.amount = amount;
	}

	public Payment () {
	}

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public Double getAmount () {
		return amount;
	}

	public void setAmount (Double amount) {
		this.amount = amount;
	}

}
