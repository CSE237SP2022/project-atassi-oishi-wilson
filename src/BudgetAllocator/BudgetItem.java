package BudgetAllocator;

public class BudgetItem {

	private String itemName;
	private double itemValue;
	private double itemCostOverTime;
	private int itemPriority;
	
	public BudgetItem(String name, double value) {
		itemName = name;
		itemValue = value;
		itemCostOverTime = 0;
		itemPriority = 0;
	}
	
	public String getName() {
		return itemName;
	}
	
	public double getValue() {
		return itemValue;
	}
	
	public double getItemCostOverTime() {
		return itemCostOverTime;
	}
	
	public void updateCostOverTime() {
		itemCostOverTime += itemValue;
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
