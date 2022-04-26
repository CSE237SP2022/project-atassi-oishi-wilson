package BudgetAllocator;

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
		budget.addItem(new BudgetItem(newItemName, newItemCost));
		return;
	} 
	
	public void addIncomeItem() {
		System.out.println("Please Input name of income Item: ");
		String newItemName = textScanner.nextLine();
		System.out.println("Please input monthly wage of income item: ");
		double newItemCost = Double.parseDouble(textScanner.nextLine());
		budget.addItem(new IncomeItem(newItemName, newItemCost));
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
	
}
