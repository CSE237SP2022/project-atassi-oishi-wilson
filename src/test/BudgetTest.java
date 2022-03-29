package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BudgetAllocator.Budget;
import BudgetAllocator.BudgetItem;

import java.util.Scanner;


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
	
	@Test
	void testRemoveItem() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00);
		budget1.addItem(item1);
		assertTrue(budget1.removeItem(item1));
	}
	
	@Test
	void testRemoveFakeItem() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00);
		assertFalse(budget1.removeItem(item1));
	}
	
	@Test
	void testGetLowestPriority() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00);
		BudgetItem item2 = new BudgetItem("BudgetItem2", 10.00);
		BudgetItem item3 = new BudgetItem("BudgetItem3", 10.00);
		item1.setPriority(0);
		item2.setPriority(1);
		item3.setPriority(2);
		budget1.addItem(item1);
		budget1.addItem(item2);
		budget1.addItem(item3);
		assertTrue(item3.equals(budget1.getExpendableItem()));
	}
	
	void testGetLowestPriorityByValue() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item1 = new BudgetItem("BudgetItem1", 50.00);
		BudgetItem item2 = new BudgetItem("BudgetItem2", 10.00);
		BudgetItem item3 = new BudgetItem("BudgetItem3", 20.00);
		item1.setPriority(0);
		item2.setPriority(1);
		item3.setPriority(1);
		budget1.addItem(item1);
		budget1.addItem(item2);
		budget1.addItem(item3);
		assertTrue(item3.equals(budget1.getExpendableItem()));
	}
	
	@Test
	void testGetNullPriority() {
		Budget budget1 = new Budget("Budget1", 100.00);
		assertTrue(null == budget1.getExpendableItem());
	}
	
	@Test
	void testGetLowestPriorities() {
		Budget budget1 = new Budget("Budget1", 100.00);
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00);
		BudgetItem item2 = new BudgetItem("BudgetItem2", 10.00);
		BudgetItem item3 = new BudgetItem("BudgetItem3", 10.00);
		item1.setPriority(0);
		item2.setPriority(1);
		item3.setPriority(1);
		budget1.addItem(item1);
		budget1.addItem(item2);
		budget1.addItem(item3);
		ArrayList<BudgetItem> lowPrioItems = new ArrayList<BudgetItem>();
		lowPrioItems.add(item2);
		lowPrioItems.add(item3);
		assertTrue(lowPrioItems.equals(budget1.getExpendableItems()));
	}
	
	@Test
	void testGetNullPriorities() {
		Budget budget1 = new Budget("Budget1", 100.00);
		ArrayList<BudgetItem> lowPrioItems = new ArrayList<BudgetItem>();
		assertTrue(lowPrioItems.equals(budget1.getExpendableItems()));
	}
}
