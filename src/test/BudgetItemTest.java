package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BudgetAllocator.BudgetItem;

public class BudgetItemTest {

		private BudgetItem item;
		
		@BeforeEach
		void setup() {
			item = new BudgetItem("BudgetItem1", 10.00);
		}
	
		@Test
		void testGetNewName() {
			assertTrue("BudgetItem1".equals(item.getName()));
		}
		
		@Test
		void testGetNewCost() {
			assertEquals(10.00, item.getValue(), 0.05);
		}
		
		@Test
		void testGetNewPriorityZero() {
			assertTrue(0 == item.getPriority());
		}
		
		@Test
		void testSetPriority() {
			item.setPriority(1);
			assertTrue(1 == item.getPriority());
		}
		
		
}
