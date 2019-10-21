package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VendingMachine {

	// data member
	static Scanner input = new Scanner(System.in);
	static Map<String, List<Product>> inventoryMap = new LinkedHashMap<String, List<Product>>();
	List<Product> itemName = new ArrayList<Product>();
	String[] itemArray;
	double price;
	Set<String> keys = inventoryMap.keySet();
	static double balance;
	static String itemSelected;
	static double change;
	static double totalSold;
	static File auditFile = new File("Audit_File.txt");
	static PrintWriter fileWriter;	
	

	public VendingMachine() {

		// call method
		File itemsFromList = new File("VendingMachine.txt");

		// pass items to constructor for item
		if (itemsFromList != null) {
			try (Scanner fileScanner = new Scanner(itemsFromList)) {
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					itemName = new ArrayList<Product>();
					for (int i = 0; i < 5; i++) {
						String[] itemArray = line.split("\\|");
						price = Double.parseDouble(itemArray[2]);
						String id = itemArray[0];
						if (itemArray[3].equals("Chip")) {
							Chips itemChips = new Chips(itemArray[1], price);
							itemName.add(itemChips);
						} else if (itemArray[3].equals("Candy")) {
							Candy itemCandy = new Candy(itemArray[1], price);
							itemName.add(itemCandy);
						} else if (itemArray[3].equals("Drink")) {
							Drink itemDrink = new Drink(itemArray[1], price);
							itemName.add(itemDrink);
						} else if (itemArray[3].equals("Gum")) {
							Gum itemGum = new Gum(itemArray[1], price);
							itemName.add(itemGum);
						}
						inventoryMap.put(id, itemName);
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found " + e.getMessage());
			}
		}
	}

	// Vending machine methods

	public void displayToCustomer() {
		for (String key : keys) {
			// try {
			if (inventoryMap.get(key).isEmpty()) {
				System.out.printf("%n %-5s SOLD OUT", key);
			} else {
				System.out.printf("%n %-5s %-20s $%.2f", key, inventoryMap.get(key).get(0).getNameOfItem(),
				inventoryMap.get(key).get(0).getPriceOfItem());
			}

		}
	}

	public void showInventory() {
		for (String key : keys) {
			int numOfItems = 0;
			String productName = null;
			List<Product> productInList = inventoryMap.get(key);
			for (Product name : productInList) {
				productName = null;
				if (productInList.contains(name)) {
					numOfItems++;
					productName = name.getNameOfItem();
				}
			}
			System.out.printf("%n %s %d", productName, numOfItems);
		}
	}

	public static void itemSound() {
		if (itemSelected.contains("A") || itemSelected.contains("a")) {
			System.out.println("Crunch Crunch, Yum!");
		} else if (itemSelected.contains("B") || itemSelected.contains("b")) {
			System.out.println("Munch Munch, Yum!");
		} else if (itemSelected.contains("C") || itemSelected.contains("c")) {
			System.out.println("Glug Glug, Yum!");
		} else if (itemSelected.contains("D") || itemSelected.contains("d")) {
			// item list can only have a,b,c,d
			System.out.println("Chew Chew, Yum!");
		}
	}

	public static void purchaseItem() {
		while (balance <= 0) {
			System.out.printf("%nYour current balance is 0. %n");
			promptUserForMoney();
		}
		System.out.printf("%nPlease select an item: ");
		itemSelected = input.nextLine().toUpperCase();
		if (inventoryMap.containsKey(itemSelected)) {
			if (!inventoryMap.get(itemSelected).isEmpty()) {
				if (balance >= inventoryMap.get(itemSelected).get(0).getPriceOfItem()) {
					balance -= (inventoryMap.get(itemSelected).get(0).getPriceOfItem());
					totalSold += inventoryMap.get(itemSelected).get(0).getPriceOfItem();
					inventoryMap.get(itemSelected).remove(0);
					itemSound();
					//createPurchaseAuditFile ();
					System.out.printf("%nYour remaining balance is: $%.2f. %n", balance);
				} else {
					System.out.printf("Insufficient funds. Please feed in more money. %n");
				}
			} else {
				System.out.printf("%s is sold out. Please make another selection.", itemSelected);
				purchaseItem();
			}
		} else {
			System.out.printf("%s is not a valid item. Please make another selection. %n", itemSelected);
			purchaseItem();
		}

	}

	public static void returnChange() {

		int numQuarters = (int) (balance * 4);
		double remainder = balance % .25;
		int numDimes = (int) (remainder * 10);
		remainder = balance % .10;
		int numNickles = (int) remainder * 20;
		System.out.printf("%nYour change is: $%.2f. You will recieve %d quaters, %d dimes, and %d nickles. %n", balance,
				numQuarters, numDimes, numNickles);
		balance = 0;

	}

	public static void promptUserForMoney() {
		System.out.printf("%nPlease insert money in whole dollars: ");
		boolean isDollar = false;
		while (!isDollar) {
			try {
				String moneyCorrectInput = input.nextLine();
				double moneyInput = Double.parseDouble(moneyCorrectInput);
				while(!(moneyInput%1==0)) {
					System.out.printf("%nYour input was not a whole number. Please insert a whole dollar amount: ");
					String isWhole = input.nextLine();
					moneyInput = Double.parseDouble(isWhole);
				} 
				balance += moneyInput;
				System.out.printf("Your current balance is: $%.2f %n", balance);
				isDollar = true;
				//createFeedMoneyAuditFile(balance, moneyInput);
			} catch (Exception e) {
				System.out.printf("%nYour input was not a number. Please insert a whole dollar amount: ");
			}
		}
	}

	public void getSalesReport() {
		for (String key : keys) {
			int numOfItems = 0;
			String productName = null;
			List<Product> productInList = inventoryMap.get(key);
			for (Product name : productInList) {
				if (productInList.contains(name)) {
					numOfItems++;
					productName = name.getNameOfItem();
				}
			}
			System.out.printf("%n %s %d", productName, numOfItems); 
			
			// should create new file
			// print all products sold: item name | total number sold
			// at bottom 
			//blank line **TOTAL SALES**
			// print $dollar amount of gross sales
		}
	}

	public static void exitProgram() {

		System.out.println("Have a great day!");
		
	}
/*
	public static void createFeedMoneyAuditFile(double balance, double moneyInputUser) {
		
		// auditFile = new File("Audit File");
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateAndTime = myDateObj.format(myFormatObj);
		try (PrintWriter fileWriter = new PrintWriter(auditFile)) {
			if(auditFile.exists()==false) {
				auditFile.createNewFile();
			} else {				
				fileWriter.print( formattedDateAndTime );
				fileWriter.printf(" FEED MONEY: $%.2f $%.2f", moneyInputUser, balance);
												
			}
			

		} catch (IOException e) {

			System.out.println("Error Will Robinson" + e.getMessage());
		} 

		// write every time money was fed in
		// include date, time, feed money, amount, current balance

	}
	
	public static void createPurchaseAuditFile () {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateAndTime = myDateObj.format(myFormatObj);
		try (PrintWriter fileWriter = new PrintWriter(auditFile)) {
			if(auditFile.exists()==false) {
				auditFile.createNewFile();
			} else {				
				fileWriter.print( formattedDateAndTime );
				fileWriter.printf(" FEED MONEY: $%.2f $%.2f");//, itemName, location, ItemPrice);
												
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	// write every time purchase is made
			// include date, time, item name, item slot,item price, remaining balance
	public static void createTransactionAuditFile () {
		
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateAndTime = myDateObj.format(myFormatObj);
		try (PrintWriter fileWriter = new PrintWriter(auditFile)) {
			if(auditFile.exists()==false) {
				auditFile.createNewFile();
			} else {				
				fileWriter.print( formattedDateAndTime );
				fileWriter.printf(" FEED MONEY: $%.2f $%.2f");//, amountOfChange, balance);
												
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	*/		

}
