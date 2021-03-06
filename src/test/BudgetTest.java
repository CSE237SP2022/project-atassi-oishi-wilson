package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BudgetAllocator.Budget;
import BudgetAllocator.BudgetItem;



public class BudgetTest {

	private Budget budget1;
	
	@BeforeEach
	void setup() {
		budget1 = new Budget("Budget1", 100.00, 12, 8000);
	}
	
	@Test
	void testGetNewName() {
		assertTrue("Budget1".equals(budget1.getName()));
	}
	
	@Test
	void testGetNewValue() {
		assertEquals(100, budget1.getValue(), .05);
	}
	
	@Test
	void testNewItem() {
		BudgetItem item = new BudgetItem("BudgetItem1", 10.00, 0);
		assertTrue(budget1.addItem(item));
	}
	
	@Test
	void testNewItemTotalCost() {
		BudgetItem item = new BudgetItem("BudgetItem1", 10.00, 0);
		budget1.addItem(item);
		assertEquals(10, budget1.getTotalCost(), .05);
	}
	
	@Test
	void testNewItemRemainingValue() {
		BudgetItem item = new BudgetItem("BudgetItem1", 10.00, 0);
		budget1.addItem(item);
		assertEquals(90, budget1.getRemainingValue(), .05);
	}
	
	
	@Test
	void testNoDuplicate() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		BudgetItem item2 = new BudgetItem("BudgetItem2", 10.00, 0);
		budget1.addItem(item1);
		assertTrue(budget1.addItem(item2));
	}
	
	@Test
	void testDuplicate() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		BudgetItem item2 = new BudgetItem("BudgetItem1", 10.00, 0);
		budget1.addItem(item1);
		assertFalse(budget1.addItem(item2));
	}
	
	@Test
	void testDuplicateTotalCost() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		BudgetItem item2 = new BudgetItem("BudgetItem1", 10.00, 0);
		budget1.addItem(item1);
		budget1.addItem(item2);
		assertEquals(10, budget1.getTotalCost(), .05);
	}
	
	@Test
	void testRemoveItem() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		budget1.addItem(item1);
		assertTrue(budget1.removeItem(item1));
	}
	
	@Test
	void testRemoveItemIsRemoved() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		budget1.addItem(item1);
		budget1.removeItem(item1);
		assertTrue(budget1.getItems().size()==0);
	}
	
	@Test
	void testRemoveFakeItem() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		assertFalse(budget1.removeItem(item1));
	}
	
	@Test
	void testGetLowestPriority() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		BudgetItem item2 = new BudgetItem("BudgetItem2", 10.00, 0);
		BudgetItem item3 = new BudgetItem("BudgetItem3", 10.00, 0);
		item1.setPriority(0);
		item2.setPriority(1);
		item3.setPriority(2);
		budget1.addItem(item1);
		budget1.addItem(item2);
		budget1.addItem(item3);
		assertTrue(item3.equals(budget1.getExpendableItem()));
	}
	
	void testGetLowestPriorityByValue() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 50.00, 0);
		BudgetItem item2 = new BudgetItem("BudgetItem2", 10.00, 0);
		BudgetItem item3 = new BudgetItem("BudgetItem3", 20.00, 0);
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
		assertTrue(null == budget1.getExpendableItem());
	}
	
	@Test
	void testGetLowestPriorities() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		BudgetItem item2 = new BudgetItem("BudgetItem2", 10.00, 0);
		BudgetItem item3 = new BudgetItem("BudgetItem3", 10.00, 0);
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
		ArrayList<BudgetItem> lowPrioItems = new ArrayList<BudgetItem>();
		assertTrue(lowPrioItems.equals(budget1.getExpendableItems()));
	}
	
	@Test
	void testGetItemByName() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 50.00, 0);
		budget1.addItem(item1);
		BudgetItem item1Copy = budget1.findItemByName("BudgetItem1");
		assertTrue(item1Copy.equals(item1));
	}
	
	@Test
	void testGetItemByNameDoesNotExist() {
		BudgetItem item1Copy = budget1.findItemByName("BudgetItem1");
		assertTrue(item1Copy == null);
	}
	
	@Test
	void testRemoveItemByName() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		budget1.addItem(item1);
		assertTrue(budget1.removeByName("BudgetItem1"));
	}
	
	@Test
	void testRemoveItemIsRemovedByName() {
		BudgetItem item1 = new BudgetItem("BudgetItem1", 10.00, 0);
		budget1.addItem(item1);
		budget1.removeByName("BudgetItem1");
		assertTrue(budget1.getItems().size()==0);
	}
}
