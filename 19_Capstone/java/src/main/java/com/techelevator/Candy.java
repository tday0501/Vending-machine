package com.techelevator;

public class Candy extends Product{
	private String nameOfItem;
	private double priceOfItem;
	private int numOfItem=5;

	public Candy (String nameOfItem, double priceOfItem) {
		super(nameOfItem, priceOfItem);
	}

}
