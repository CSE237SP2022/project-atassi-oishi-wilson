package BudgetAllocator;

import java.io.*;
import java.util.*;

public class AllocMain {

	public static void main(String[] args) {
		boolean correctFile = false;

		Scanner textScanner = new Scanner(System.in);

		System.out.println("Please input your exact txt file name that is in this directory: ");
		Scanner txtParser;
		int whichLine = 0;
		int monthDuration = 0;
		String name = "";
		double initialSavings = 0;
		double income = 0;
		ArrayList<String> itemNames = new ArrayList<String>();
		ArrayList<Double> itemCosts = new ArrayList<Double>();
		String fileName = textScanner.nextLine();
		try {
			txtParser = new Scanner(new File("F:\\" + fileName + ".txt"));
			correctFile = true;
			// CSVParser.useDelimiter(",");
			while (txtParser.hasNext()) {
				switch (whichLine) {
				case 1:
					whichLine = 0;
					monthDuration = txtParser.nextInt();
					whichLine++;
					break;
				case 2:
					whichLine = 1;
					String thisString = txtParser.nextLine();
					Scanner sc = new Scanner(thisString);
					name = sc.next();
					initialSavings = Double.parseDouble(sc.next());
					income = Double.parseDouble(sc.next());
					whichLine++;
					break;
				case 3:
					whichLine = 2;
					String thisBudgetItem = txtParser.nextLine();
					Scanner sc2 = new Scanner(thisBudgetItem);
					String itemName = sc2.next();
					double value = Double.parseDouble(sc2.next());
					itemNames.add(itemName);
					itemCosts.add(value);
					break;
				}
			}
			txtParser.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found, please enter again.");
		}
		Budget budget = new Budget(name, initialSavings, monthDuration, income);
		for (int i = 0; i < itemNames.size(); i++) {
			budget.addItem(new BudgetItem(itemNames.get(i), itemCosts.get(i)));
		}
		
		boolean donePicking = false;
		for (int i = 0; i < monthDuration; i++) {
			while (!donePicking) {
				System.out.println("Would you like to add a budget item or income source? Select 1");
				System.out.println("Would you like to remove a budget item? Select 2");
				System.out.println("Would you like to update your entered income? Select 3");
				System.out.println("Would you like to proceed to next month? Select 4");
				int choice = textScanner.nextInt();
				if (!(choice >= 1 && choice <= 4)) {
					System.out.println("Invalid Choice: Please enter again.");
				} else if (choice == 1) { //add budget item
					System.out.println("Would you like to add an income item or a budget item? Enter 'i' for income or 'b' for budget");
					char incomeOrBudget = textScanner.next().charAt(0);
					while(incomeOrBudget != 'i' && incomeOrBudget != 'b') {
						System.out.println("Please enter either 'i' for income item or 'b' for budget item");
						incomeOrBudget = textScanner.next().charAt(0);
					}
					if(incomeOrBudget == 'i') {
						System.out.println("Please Input name of income Item: ");
						String newItemName = textScanner.nextLine();
						System.out.println("Please input monthly wage of income item: ");
						double newItemCost = textScanner.nextInt();
						budget.addItem(new IncomeItem(newItemName, newItemCost));
					}else if(incomeOrBudget == 'b'){
						System.out.println("Please Input name of budget Item: ");
						String newItemName = textScanner.nextLine();
						System.out.println("Please input monthly cost of budget item: ");
						double newItemCost = textScanner.nextInt();
						budget.addItem(new BudgetItem(newItemName, newItemCost));
					}
				} else if (choice == 2) { //remove budget item
					System.out.println("Please list name of budget item to remove: ");
					String removedName = textScanner.nextLine();
					ArrayList<BudgetItem> items = budget.getItems();
					boolean isInList = false;
					for(int k = 0; k < items.size(); k++) {
						if(items.get(k).getName().equals(removedName)) {
							isInList = true;
							items.remove(k);
						}
					}
					if(!isInList) {
						System.out.println("No budget item with that name was located in list, please select option to try again.");
					}
				} else if (choice == 3) { //update income
						System.out.println("What is your new monthly income?");
						double newIncome = textScanner.nextInt();
						budget.setIncome(newIncome);

				} else { //proceed to next month
					donePicking = true;
				}
			}
		}

	}
}
