package BudgetAllocator;

public class BudgetItem {

	private String itemName;
	private double itemValue;
	private int itemPriority;
	
	public BudgetItem(String name, double value) {
		itemName = name;
		itemValue = value;
		itemPriority = 0;
	}
	
	public String getName() {
		return itemName;
	}
	
	public double getValue() {
		return itemValue;
	}

	public int getPriority() {
		return itemPriority;
	}

	public void setPriority(int i) {
		itemPriority = i;
	}
}
