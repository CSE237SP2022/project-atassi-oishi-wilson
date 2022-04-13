package BudgetAllocator;

public class IncomeItem extends BudgetItem {

	private String itemName;
	private double itemValue;
	private double itemCostOverTime;
	private int itemPriority;
	
	public IncomeItem(String name, double value) {
		super(name, value);
		// TODO Auto-generated constructor stub
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
	
	public boolean equals(IncomeItem otherItem) {
		if (itemName == otherItem.getName() && itemValue == otherItem.getValue() && itemPriority == otherItem.getPriority()) {
			return true;
		}
		return false;
	}
}
