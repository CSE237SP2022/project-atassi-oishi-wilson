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
		double income = 0 ;
		ArrayList<String> itemNames = new ArrayList<String>();
		ArrayList<Double> itemCosts = new ArrayList<Double>();
		String fileName = textScanner.nextLine();
		//System.out.println(fileName+".txt");
		try {
			File directory = new File("./" + fileName + ".txt");
			//txtParser = new Scanner(new File("/Users/ferrisatassi/Desktop/project-atassi-oishi-wilson/src/BudgetAllocator/TestFile.txt"));
			txtParser = new Scanner(directory);
			correctFile = true;
			// CSVParser.useDelimiter(",");
			while (txtParser.hasNext()) {
				switch (whichLine) {
				case 0:
					monthDuration = txtParser.nextInt();
					whichLine++;
					break;
				case 1:
					name = txtParser.next();
					initialSavings = Double.parseDouble(txtParser.next());
					income = Double.parseDouble(txtParser.next());
					whichLine++;
					break;
				case 2:
					String itemName = txtParser.next();
					double value = Double.parseDouble(txtParser.next());
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
			donePicking = false;
			while (!donePicking) {
				System.out.println("Would you like to add a budget item or income source? Select 1");
				System.out.println("Would you like to remove a budget item? Select 2");
				System.out.println("Would you like to proceed to next month? Select 3");
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
					if(incomeOrBudget == 'i') { //choice between income or budget
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
				} else if (choice == 2) { //remove item
					System.out.println("Please list name of item to remove: ");
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
				} else { //proceed to next month and print current
					donePicking = true;
					System.out.println("------------------------Month " + (i + 1) + "------------------------");
					for(int j = 0; j < budget.getItems().size(); j++) {
						budget.getItems().get(j).updateCostOverTime();
						if(!budget.getItems().get(j).isIncomeItem()){
							System.out.println("Budget Item:   " + budget.getItems().get(j).getName() + ": Cost = " + budget.getItems().get(j).getValue() + "   "
									+ "Total Cost from This: " + (budget.getItems().get(j).getItemCostOverTime()));
						} else {
							System.out.println("Income Item:   " + budget.getItems().get(j).getName() + ": Cost = " + budget.getItems().get(j).getValue() + "   "
									+ "Total Income from This: " + (budget.getItems().get(j).getItemCostOverTime()));
						}
					} 
					System.out.println("Total Budget Cost this Month: " + budget.calcTotalCost());
					System.out.println("Total Budget Income this Month: " + budget.calcTotalIncome());
					budget.addIncometoBudgetValue();
					System.out.println("Total Savings after This Month: " + budget.getValue());
					System.out.println("-------------------------------------------------------");
				}
			}
		}

	}
}
