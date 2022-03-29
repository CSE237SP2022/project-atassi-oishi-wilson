package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BudgetAllocator.Budget;
import BudgetAllocator.BudgetItem;

public class BudgetTest {

	@Test
	void testGetNewName() {
		Budget budget1 = new Budget("Budget1", 100.00);
		assertTrue("Budget1".equals(budget1.getName()));
	}
	
	@Test
	void testGetNewValue() {
		Budget budget1 = new Budget("Budget1", 100.00);
		assertEquals(100, budget1.getValue(), .05);
	}
	
	@Test
	void testNewItem() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item = new BudgetItem("BudgetItem1", 10.00);
		assertTrue(budget1.addItem(item));
	}
	
	@Test
	void testNewItemTotalCost() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item = new BudgetItem("BudgetItem1", 10.00);
		budget1.addItem(item);
		assertEquals(10, budget1.getTotalCost(), .05);
	}
	
	@Test
	void testNewItemRemainingValue() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item = new BudgetItem("BudgetItem1", 10.00);
		budget1.addItem(item);
		assertEquals(90, budget1.getRemainingValue(), .05);
	}
	
	
	@Test
	void testNoDuplicate() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00);
		BudgetItem item2 = new BudgetItem("BudgetItem2", 10.00);
		budget1.addItem(item1);
		assertTrue(budget1.addItem(item2));
	}
	
	@Test
	void testDuplicate() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00);
		BudgetItem item2 = new BudgetItem("BudgetItem1", 10.00);
		budget1.addItem(item1);
		assertFalse(budget1.addItem(item2));
	}
	
	@Test
	void testDuplicateTotalCost() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00);
		BudgetItem item2 = new BudgetItem("BudgetItem1", 10.00);
		budget1.addItem(item1);
		budget1.addItem(item2);
		assertEquals(10, budget1.getTotalCost(), .05);
	}
}
