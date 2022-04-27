package BudgetAllocator;

public class BudgetItem {

	private String itemName;
	private double itemValue;
	private double itemCostOverTime;
	private int itemPriority;
	private boolean isIncome;
	
	public BudgetItem(String name, double value, int prio) {
		itemName = name;
		itemValue = value;
		itemCostOverTime = 0;
		itemPriority = prio;
		isIncome = false;
	}
	
	public BudgetItem(String name, double value, boolean isIncome) {
		itemName = name;
		itemValue = value;
		itemCostOverTime = 0;
		itemPriority = 0;
		this.isIncome = isIncome;
	}
	
	public String getName() {
		return itemName;
	}
	
	public double getValue() {
		if(isIncome) {
			return -itemValue;
		}
		return itemValue;
	}
	
	public boolean isIncomeItem() {
		return isIncome;
	}
	
	public double getItemCostOverTime() {
		return itemCostOverTime;
	}
	
	public void updateCostOverTime() {
		if(isIncome) {
			itemCostOverTime -= itemValue;
		} else {
			itemCostOverTime += itemValue;
		}
	}

	public int getPriority() {
		return itemPriority;
	}

	public void setPriority(int i) {
		itemPriority = i;
	}
	
	public boolean equals(BudgetItem otherItem) {
		if (itemName == otherItem.getName() && itemValue == otherItem.getValue() && itemPriority == otherItem.getPriority()) {
			return true;
		}
		return false;
	}
}
