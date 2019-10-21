package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Product {

	// Data members
	private String nameOfItem;

	private double priceOfItem;

	// Constructor
	public Product(String nameOfItem, double priceOfItem) {
		this.nameOfItem = nameOfItem;
		this.priceOfItem = priceOfItem;
	}

	// Getters and setters
	public String getNameOfItem() {
		return nameOfItem;
	}

	public double getPriceOfItem() {
		return priceOfItem;
	}

}
