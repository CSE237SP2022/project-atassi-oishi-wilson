package BudgetAllocator;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class Budget {

	private String budgetName;
	private double budgetValue;
	private ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();
	private double totalCost = 0;
	
	public Budget(String name, double value) {
		budgetName = name;
		budgetValue = value;	
	}

	public String getName() {
		return budgetName;
	}
	
	public double getValue() {
		return budgetValue;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	public boolean addItem(BudgetItem item) {
		if(this.checkForDuplicates(item)) {
			return false;
		}
		items.add(item);
		totalCost = calcTotalCost();
		return true;
	}
	
	public double calcTotalCost() {
		double totalValue = 0;
		for (int i = 0; i < items.size(); i++) {
			totalValue += items.get(i).getValue();
		}
		return totalValue;
	}
	
	public double getRemainingValue() {
		return budgetValue - totalCost;
	}
	
	public boolean checkForDuplicates(BudgetItem item) {
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).equals(item)) {
				return true;
			}
		}
		return false;
	}

	public boolean removeItem(BudgetItem item) {
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).equals(item)) {
				items.remove(i);
				totalCost = calcTotalCost();
				return true;
			}
		}
		return false;
	}

	public BudgetItem getExpendableItem() {
		if(items.size() == 0) {
			return null;
		}
		BudgetItem worstItem = items.get(0);
		BudgetItem iterItem;
		for (int i = 0; i < items.size(); i++) {
			iterItem = items.get(i);
			if(iterItem.getPriority() > (worstItem.getPriority())) {
				worstItem = iterItem;
			}
			if(iterItem.getPriority() == (worstItem.getPriority())) {
				if(iterItem.getValue() > worstItem.getValue()) {
					worstItem = iterItem;
				}
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
			if(iterItem.getPriority() > lowestPriority) {
				lowestPriority = iterItem.getPriority();
				lowPrioItems.clear();
				lowPrioItems.add(iterItem);
			}
			else if(iterItem.getPriority() == lowestPriority) {
				lowPrioItems.add(iterItem);
			}
			
		}
		return lowPrioItems;
	}
	
	
}
