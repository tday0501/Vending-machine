package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT_VENDING_MACHINE = "Exit";
	private static final String MAIN_MENU_SHOW_INVENTORY = "Show Inventory";
	private static final String MAIN_MENU_SALES_REPORT = "Sales Report";
	private static final String MAIN_SUBMENU_FEED_MONEY = "Feed Money";
	private static final String MAIN_SUBMENU_SELECT_PRODUCT = "Select Product";
	private static final String MAIN_SUBMENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_EXIT_VENDING_MACHINE, MAIN_MENU_SHOW_INVENTORY, MAIN_MENU_SALES_REPORT };
	private static final String[] MAIN_SUBMENU_OPTIONS = { MAIN_SUBMENU_FEED_MONEY, MAIN_SUBMENU_SELECT_PRODUCT,
			MAIN_SUBMENU_FINISH_TRANSACTION };

	// select purchase items
	// prompted with sub menus
	// 1) feed money
	// 2) select product
	// 3) finish transaction

	private Menu menu;
	VendingMachine vendingMachine = new VendingMachine();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendingMachine.displayToCustomer();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					String choiceTwo = (String) menu.getChoiceFromOptions(MAIN_SUBMENU_OPTIONS);
					if (choiceTwo.equals(MAIN_SUBMENU_FEED_MONEY)) {
						VendingMachine.promptUserForMoney();
						
					} else if (choiceTwo.equals(MAIN_SUBMENU_SELECT_PRODUCT)) {
						VendingMachine.purchaseItem();
					} else if (choiceTwo.equals(MAIN_SUBMENU_FINISH_TRANSACTION)) {
						// go back to main menu?
						// give change - "Have a good day" and exit program completely?
						VendingMachine.returnChange();						
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_EXIT_VENDING_MACHINE)) {
				VendingMachine.exitProgram();
				break;
			} else if (choice.equals(MAIN_MENU_SHOW_INVENTORY)) {
				vendingMachine.showInventory();
			} else if (choice.equals(MAIN_MENU_SALES_REPORT)) {
				vendingMachine.getSalesReport();
			}
		}
	}

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

	}
}
