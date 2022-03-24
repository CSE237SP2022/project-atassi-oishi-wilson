package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BudgetAllocator.BudgetItem;

public class BudgetItemTest {

		@Test
		void testGetNewName() {
			BudgetItem item = new BudgetItem("BudgetItem1", 10.00);
			assertTrue("BudgetItem1".equals(item.getName()));
		}
		
		@Test
		void testGetNewCost() {
			BudgetItem item = new BudgetItem("BudgetItem1", 10.00);
			assertEquals(10.00, item.getValue(), 0.05);
		}
		
}
