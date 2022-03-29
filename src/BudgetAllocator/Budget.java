package BudgetAllocator;

import java.util.ArrayList;

public class Budget {

	private String budgetName;
	private double budgetValue;
	private ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();
	
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
	
	public void addItem(BudgetItem item) {
		items.add(item);
	}
	
	public double getTotalCost() {
		double totalValue = 0;
		for (int i = 0; i < items.size(); i++) {
			totalValue += items.get(i).getValue();
		}
		return totalValue;
	}
	
	public double getRemainingValue() {
		return budgetValue - this.getTotalCost();
	}
}
