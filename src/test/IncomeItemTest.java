package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BudgetAllocator.IncomeItem;

public class IncomeItemTest {

		private IncomeItem item;
		
		@BeforeEach
		void setup() {
			item = new IncomeItem("IncomeItem1", 10.00);
		}
	
		@Test
		void testGetNewName() {
			assertTrue("IncomeItem1".equals(item.getName()));
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
		
		@Test
		void testEquals() {
			IncomeItem item2 = new IncomeItem("BudgetItem1", 10.00);
			assertTrue(item.equals(item2));
		}
		
		
}
