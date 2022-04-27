package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BudgetAllocator.Budget;
import BudgetAllocator.BudgetItem;
import BudgetAllocator.Menu;
import java.util.*;



public class MenuTest {

	private Menu menu;
	
	@BeforeEach
	void setup() {
		Scanner scanner = new Scanner(System.in);
		Budget budget = new Budget("Budget1", 100.00, 12, 8000);
		menu = new Menu(budget, scanner);
	}
	
	@Test
	void testAddItem() {
		int prevSize = menu.getBudget().getItems().size();
		menu.addBudgetItem();
		assertTrue((prevSize + 1) == menu.getBudget().getItems().size());
	}
	
	@Test 
	void testRemoveItem() {
		int prevSize = menu.getBudget().getItems().size();
		menu.removeItem();
		assertTrue((prevSize - 1) == menu.getBudget().getItems().size());
	}
	
	@Test
	void testSetBudget() {
		Budget budget1 = menu.getBudget();
		Budget budget2 = new Budget("Budget2", 100.00, 12, 8000);
		menu.setBudget(budget2);
		assertTrue(!(budget1.equals(menu.getBudget())));
	}
	
	@Test
	void testGetBudget() {
		Budget budget2 = new Budget("Budget2", 100.00, 12, 8000);
		menu.setBudget(budget2);
		assertTrue(budget2.equals(menu.getBudget()));
	}
	
	
}
