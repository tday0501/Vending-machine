package com.techelevator;

import java.util.List;
import java.util.Map;

public class InventoryOfProducts  {
	
	
	private String itemSelectionId;
	private double priceOfItem;
	private String typeOfItem;
	
	private int numOfItem = 5;
	
	List<Product> vendingItems;
	Map<Product, List> vendingMachineOutput;

	// create product as an object(3 strings and a double)
	// add product to list
	
	public InventoryOfProducts (Product temp) {
		
		
		
		if (typeOfItem.equals("Chip"))  {
			 Object item;
		 }
		
		
	}
	
	
	public List <Product> getVendingItems () {
		return vendingItems;		
	}	
	
	public int getNumOfItem() {
		return numOfItem;
	}

	// update inventory on purchase
	public void setNumOfItem(int numOfItem) {
		this.numOfItem = numOfItem;
	}

}
