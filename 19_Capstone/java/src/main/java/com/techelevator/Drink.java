package com.techelevator;

public class Drink extends Product{
	
	private String nameOfItem;
	private double priceOfItem;
	private int numOfItem=5;

	public Drink (String nameOfItem, double priceOfItem) {
		super(nameOfItem, priceOfItem);
	}

}
