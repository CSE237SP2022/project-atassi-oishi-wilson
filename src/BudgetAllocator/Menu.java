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

	public boolean singleMonthIterationContinue(int month) {
		boolean doneWithMonth = false;
		while (!doneWithMonth) {
			int choiceResult = this.pickItem(month);
			switch (choiceResult) {
			case 1:
				return true;
			case 2:
				return false;
			default:
				break;
			}
		}
		return false;
	}

	public int pickItem(int month) {
		promptUser();
		String userInput = textScanner.nextLine();
		int choice = Integer.parseInt(userInput);
		switch (choice) {
		case 1:
			choiceToAdd();
			return 0;
		case 2:
			this.removeItem();
			return 0;
		case 3:
			this.printInterface(month);
			return 1;
		case 4:
			return 2;
		default:
			System.out.println("Invalid Choice: Please enter again.");
			return 0;
		}
	}
	
	public void promptUser() {
		System.out.println("Would you like to add a budget item or income source? Select 1");
		System.out.println("Would you like to remove a budget item? Select 2");
		System.out.println("Would you like to proceed to next month? Select 3");
		System.out.println("Would you like to quit the program? Select 4");
	}

	public void choiceToAdd() {
		System.out.println("Would you like to add an income item or a budget item? Enter 'i' for income or 'b' for budget");
		char incomeOrBudget = textScanner.next().charAt(0);
		textScanner.nextLine();
		while (incomeOrBudget != 'i' && incomeOrBudget != 'b') {
			System.out.println("Please enter either 'i' for income item or 'b' for budget item");
			incomeOrBudget = textScanner.next().charAt(0);
		}
		if (incomeOrBudget == 'i') { // choice between income or budget
			this.addIncomeItem();
		} else if (incomeOrBudget == 'b') {
			this.addBudgetItem();
		}
	}
	
	public void addBudgetItem() {
		System.out.println("Please Input name of budget Item: ");
		String newItemName = textScanner.nextLine();
		System.out.println("Please input monthly cost of budget item: ");
		double newItemCost = Double.parseDouble(textScanner.nextLine());
		System.out.println(
				"Please input a priority (priority of 0 has the least likelihood of removal from the budget): ");
		int newPrioValue = Integer.parseInt(textScanner.nextLine());
		if(!(newItemCost >= budget.getValue())) {
			addItemOverBudget(newItemName, newItemCost, newPrioValue);
			budget.addItem(new BudgetItem(newItemName, newItemCost, newPrioValue));
			return;
		} else {
			System.out.println("Cannot add this budget item. Budget item cost exceeds total savings.");
		}
	}

	public void addItemOverBudget(String name, double cost, int priority) {
		while (budget.calcTotalCost() + cost > budget.getValue()) {
			System.out.println("Your savings are now: " + budget.getValue() + ". Your total budget cost is: "
					+ (budget.calcTotalCost() + cost) + ". You are over budget by: "
					+ Math.abs(budget.getValue() - (budget.calcTotalCost() + cost)) + ".");
			System.out.println(
					"Your budget has been exceeded with the addition of this item. Would you like to remove one of your lowest priority items? y/n");
			String input = textScanner.nextLine();
			if (input.equals("y") || input.equals("yes")) {
				removeFromExpendableList();
				System.out.println("Item removed!");
			} else if (input.equals("n") || input.equals("no")) {
				System.out.println("The item was not added.");
			}
		}
	}

	public void addIncomeItem() {
		System.out.println("Please Input name of income Item: ");
		String newItemName = textScanner.nextLine();
		System.out.println("Please input monthly wage of income item: ");
		double newItemCost = Double.parseDouble(textScanner.nextLine());
		budget.addItem(new BudgetItem(newItemName, -newItemCost, true));
		return;
	}

	public void removeFromExpendableList() {
		String nameToRemove = "";
		ArrayList<BudgetItem> lowPrioItems = budget.getExpendableItems();
		ArrayList<String> lowPrioNames = new ArrayList<String>();
		for (BudgetItem lowPrioItem : lowPrioItems) {
			System.out.println(lowPrioItem.getName());
			lowPrioNames.add(lowPrioItem.getName());
		}
		System.out.println("Please type the name of the item you would like to remove from this low priority list: ");
		boolean correctName = false;
		while (!correctName) {
			nameToRemove = textScanner.nextLine();
			if (lowPrioNames.contains(nameToRemove)) {
				correctName = true;
			}
		}
		budget.removeByName(nameToRemove);
	}

	public void removeItem() {
		System.out.println("Please list name of item to remove: ");
		String removedName = textScanner.nextLine();
		boolean result = budget.removeByName(removedName);
		if (!result) {
			System.out.println("No budget item with that name was located in list, please select option to try again.");
		}
		return;
	}

	public void printInterface(int month) {
		System.out.println("------------------------Month " + (month + 1) + "------------------------");
		for (int j = 0; j < budget.getItems().size(); j++) {
			budget.getItems().get(j).updateCostOverTime();
			if (!budget.getItems().get(j).isIncomeItem()) {
				System.out.println("Budget Item:   " + budget.getItems().get(j).getName() + ": Cost = "
						+ budget.getItems().get(j).getValue() + "   " + "Total Cost from This: "
						+ (budget.getItems().get(j).getItemCostOverTime()));
			} else {
				System.out.println("Income Item:   " + budget.getItems().get(j).getName() + ": Wage = "
						+ budget.getItems().get(j).getValue() + "   " + "Total Income from This: "
						+ (budget.getItems().get(j).getItemCostOverTime()));
			}
		}
		System.out.println("Total Budget Cost this Month: " + budget.calcTotalCost());
		System.out.println("Total Budget Income this Month: " + budget.calcTotalIncome());
		budget.changeBudget();
		System.out.println("Total Savings after This Month: " + budget.getValue());
		System.out.println("-------------------------------------------------------");
	}

}
