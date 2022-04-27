package BudgetAllocator;

import java.io.*;
import java.util.*;

public class AllocMain {

	private static int monthDuration = 0;
	private static String name = "";
	private static double initialSavings = 0;
	private static double income = 0;
	
	static ArrayList<String> itemNames = new ArrayList<String>();
	static ArrayList<Double> itemCosts = new ArrayList<Double>();
	static ArrayList<Integer> itemPrios = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		Scanner textScanner = new Scanner(System.in);
		
		parseFile(textScanner);
		
		Budget budget = new Budget(name, initialSavings, monthDuration, income);
		
		for (int i = 0; i < itemNames.size(); i++) {
			budget.addItem(new BudgetItem(itemNames.get(i), itemCosts.get(i), itemPrios.get(i)));
		}
		
		Menu menu = new Menu(budget, textScanner);

		boolean userQuit = false;
		
		boolean donePicking = false;
		for (int i = 0; i < monthDuration; i++) {
			userQuit = !menu.singleMonthIterationContinue(i);
			if(userQuit) {
				break;
			}
		}
		System.out.println("Thank you for using our Budget Allocator!");
	}
	
	public static void parseFile(Scanner textScanner) {
		boolean correctFile = false;

		Scanner txtParser;
		int whichLine = 0;
		
		while(!correctFile) {
			try {
				
				System.out.println("Please input your exact txt file name that is in this directory: ");
				String fileName = textScanner.nextLine();
				//fileName = "../" + fileName + ".txt";
				fileName = fileName + ".txt";
				//System.out.println(new File("").getAbsolutePath());
				File directory = new File(fileName);
				//System.out.println(directory.getAbsolutePath());
				txtParser = new Scanner(directory);
				//txtParser = new Scanner(new File("/Users/ferrisatassi/Desktop/project-atassi-oishi-wilson/src/BudgetAllocator/TestFile.txt"));
				while (txtParser.hasNext()) {
					switch (whichLine) {
					case 0:
						monthDuration = txtParser.nextInt();
						whichLine++;
						break;
					case 1:
						name = txtParser.next();
						initialSavings = Double.parseDouble(txtParser.next());
						income = Double.parseDouble(txtParser.next());
						whichLine++;
						break;
					case 2:
						String itemName = txtParser.next();
						double value = Double.parseDouble(txtParser.next());
						int prio = Integer.parseInt(txtParser.next());
						itemNames.add(itemName);
						itemCosts.add(value);
						itemPrios.add(prio);
						break;
					}
				}
				txtParser.close();
				correctFile = true;
			} catch (FileNotFoundException e) {
				System.out.println("File not found, please enter again.");
			}
		}
	}
	
}
