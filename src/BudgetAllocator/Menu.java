package BudgetAllocator;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private Budget budget;
	private Scanner textScanner;
	
	public Menu(Budget thisBudget, Scanner scanner) {
		budget = thisBudget;
		textScanner = scanner;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	
	
	public void addBudgetItem() {
		System.out.println("Please Input name of budget Item: ");
		String newItemName = textScanner.nextLine();
		System.out.println("Please input monthly cost of budget item: ");
		double newItemCost = Double.parseDouble(textScanner.nextLine());
		System.out.println("Please input a priority (priority of 0 has the least likelihood of removal from the budget): ");
		int newPrioValue = textScanner.nextInt();
		while(budget.calcTotalCost() + newItemCost > budget.getValue()) {
			System.out.println("Your savings are now: " + budget.getValue() + ". Your total budget cost is: " + (budget.calcTotalCost() + newItemCost) + ". You are over budget by: " + Math.abs(budget.getValue() - (budget.calcTotalCost() + newItemCost)) + ".");
			System.out.println("Your budget has been exceeded with the addition of this item. Would you like to remove one of your lowest priority items? y/n");
			String input = textScanner.nextLine();
			if(input.equals("y") || input.equals("yes")) {
				ArrayList<BudgetItem> lowPrioItems = budget.getExpendableItems();
				ArrayList<String> lowPrioNames = new ArrayList<String>();
				for(BudgetItem lowPrioItem : lowPrioItems) {
					System.out.println(lowPrioItem.getName());
					lowPrioNames.add(lowPrioItem.getName());
				}
				System.out.println("Please type the name of the item you would like to remove from this low priority list: ");
				boolean correctName = false;
				while(!correctName) {
					input = textScanner.nextLine();
					if(lowPrioNames.contains(input)) {
						correctName = true;
					}
				}
				
				budget.removeByName(input);
				
				budget.addItem(new BudgetItem(newItemName, newItemCost, newPrioValue));
				System.out.println("Item removed!");
			}else if(input.equals("n") || input.equals("no")) {
				System.out.println("The item was not added.");
			}
		}
		return;
	} 
	
	public void addIncomeItem() {
		System.out.println("Please Input name of income Item: ");
		String newItemName = textScanner.nextLine();
		System.out.println("Please input monthly wage of income item: ");
		double newItemCost = Double.parseDouble(textScanner.nextLine());
		budget.addItem(new BudgetItem(newItemName, -newItemCost, true));
		return;
	}
	
	public void removeItem() {
		System.out.println("Please list name of item to remove: ");
		String removedName = textScanner.nextLine();
		boolean result = budget.removeByName(removedName);
		if(!result) {
			System.out.println("No budget item with that name was located in list, please select option to try again.");
		}
		return;
	}
	
	public void printInterface(int month) {
		System.out.println("------------------------Month " + (month + 1) + "------------------------");
		for(int j = 0; j < budget.getItems().size(); j++) {
			budget.getItems().get(j).updateCostOverTime();
			if(!budget.getItems().get(j).isIncomeItem()){
				System.out.println("Budget Item:   " + budget.getItems().get(j).getName() + ": Cost = " + budget.getItems().get(j).getValue() + "   "
						+ "Total Cost from This: " + (budget.getItems().get(j).getItemCostOverTime()));
			} else {
				System.out.println("Income Item:   " + budget.getItems().get(j).getName() + ": Wage = " + budget.getItems().get(j).getValue() + "   "
						+ "Total Income from This: " + (budget.getItems().get(j).getItemCostOverTime()));
			}
		} 
		System.out.println("Total Budget Cost this Month: " + budget.calcTotalCost());
		System.out.println("Total Budget Income this Month: " + budget.calcTotalIncome());
		budget.changeBudget();
		System.out.println("Total Savings after This Month: " + budget.getValue());
		System.out.println("-------------------------------------------------------");
	}
	
}
