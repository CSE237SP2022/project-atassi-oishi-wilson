package BudgetAllocator;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class Budget {

	private String budgetName;
	private double budgetValue;
	private ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();
	private double totalCost;
	private int months;
	private double income;

	public Budget(String name, double value, int months, double income) {
		budgetName = name;
		budgetValue = value;
		this.months = months;
		this.income = income;
		totalCost = 0;
	}

	public String getName() {
		return budgetName;
	}

	public int getMonths() {
		return months;
	}

	public ArrayList<BudgetItem> getItems() {
		return items;
	}

	public double getValue() {
		return budgetValue;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void updateIncome(double income) {
		this.income += income;
	}

	public boolean addItem(BudgetItem item) {
		if (this.checkForDuplicates(item)) {
			return false;
		}
		items.add(item);
		totalCost = calcTotalCost();
		return true;
	}

	public double calcTotalCost() {
		double totalValue = 0;
		for (int i = 0; i < items.size(); i++) {
			if (!items.get(i).isIncomeItem()) {
				totalValue += items.get(i).getValue();
			}
		}
		return totalValue;
	}

	public double calcTotalIncome() {
		double totalIncome = income;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).isIncomeItem()) {
				totalIncome += items.get(i).getValue();
			}
		}
		return (totalIncome);
	}

	public double getRemainingValue() {
		return budgetValue - totalCost;
	}

	public boolean checkForDuplicates(BudgetItem item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				return true;
			}
		}
		return false;
	}

	public boolean removeItem(BudgetItem item) {
		System.out.println("removeItem is called with: " + item.getName());
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				items.remove(i);
				totalCost = calcTotalCost();
				return true;
			}
		}
		return false;
	}

	public BudgetItem getExpendableItem() {
		ArrayList<BudgetItem> lowPrioItems = this.getExpendableItems();

		if (lowPrioItems.size() == 0) {
			return null;
		}
		BudgetItem worstItem = lowPrioItems.get(0);
		BudgetItem iterItem;
		for (int i = 0; i < lowPrioItems.size(); i++) {
			iterItem = lowPrioItems.get(i);
			if (iterItem.getValue() > worstItem.getValue()) {
				worstItem = iterItem;
			}

		}
		return worstItem;
	}

	public ArrayList<BudgetItem> getExpendableItems() {
		ArrayList<BudgetItem> lowPrioItems = new ArrayList<BudgetItem>();
		BudgetItem iterItem;
		int lowestPriority = -1;
		for (int i = 0; i < items.size(); i++) {
			iterItem = items.get(i);
			if (!iterItem.isIncomeItem()) {
				addToLowPrioItems(lowestPriority, iterItem, lowPrioItems);
			}
		}
		return lowPrioItems;
	}
	
	public void addToLowPrioItems(int lowestPriority, BudgetItem iterItem, ArrayList<BudgetItem> lowPrioItems) {
		if (iterItem.getPriority() > lowestPriority) {
			lowestPriority = iterItem.getPriority();
			lowPrioItems.clear();
			lowPrioItems.add(iterItem);
		} else if (iterItem.getPriority() == lowestPriority) {
			lowPrioItems.add(iterItem);
		}
	}

	public void changeBudget() {
		budgetValue += income;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).isIncomeItem()) {
				budgetValue += items.get(i).getValue();
			} else {
				budgetValue -= items.get(i).getValue();
			}
		}
	}

	public BudgetItem findItemByName(String inputName) {
		BudgetItem iterItem;
		for (int i = 0; i < items.size(); i++) {
			iterItem = items.get(i);
			if (iterItem.getName().equals(inputName)) {
				return iterItem;
			}
		}
		return null;
	}

	public boolean removeByName(String inputName) {
		BudgetItem namedItem = this.findItemByName(inputName);
		if (namedItem == null) {
			return false;
		}
		System.out.println("Calling removeItem with: " + namedItem.getName());
		return this.removeItem(namedItem);
	}

}
