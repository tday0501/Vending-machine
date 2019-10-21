package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		// call method
		File itemsFromList = getInputFileFromUser();
		
		// pass items to constructor for item
		if (itemsFromList != null) {
			try (Scanner fileScanner = new Scanner(itemsFromList)) {
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
				//	Product itemInList = new Product(line);
				//	InventoryOfProducts list = new InventoryOfProducts(itemInList);
					// print one item
			//	System.out.println(list.getVendingItems ());
				}
			} catch (FileNotFoundException e) {

				System.out.println("File not found " + e.getMessage());
			}

		}

	}

	// Methods
	// prompt user
	private static File getInputFileFromUser() {

		File inputFile = null;
		try {
			// Prompt user for file
			//System.out.print("Please enter a file path for your list of items: ");
			String path = "VendingMachine.txt";

			inputFile = new File(path);
			if (inputFile.exists() == false) { // checks for the existence of a file
				System.out.println(path + " does not exist");
				inputFile = null;
			} else if (inputFile.isFile() == false) {
				System.out.println(path + " is not a file");
				inputFile = null;
			}
		}
	catch (Exception e){
			System.out.println("Error " + e.getMessage());
		}
		finally {
			return inputFile;
		}
	}

}
