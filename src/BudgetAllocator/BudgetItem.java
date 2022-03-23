package BudgetAllocator;

public class BudgetItem {

	private String itemName;
	private double itemValue;
	
	public BudgetItem(String name, double value) {
		itemName = name;
		itemValue = value;
	}
	
	public String getName() {
		return itemName;
	}
	
	public double getValue() {
		return itemValue;
	}
}
