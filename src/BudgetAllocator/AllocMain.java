package BudgetAllocator;

import java.io.*;
import java.util.*;


public class AllocMain {

	public static void main(String[] args) {
		boolean correctFile = false;

		Scanner textScanner = new Scanner(System.in);

		System.out.println("Please input your exactnCSV file name that is in this directory: ");
		Scanner CSVParser;
		int whichLine = 0;
		int monthDuration = 0;
		String name = "";
		double initialSavings = 0;
		double income = 0;
		ArrayList<String> itemNames = new ArrayList<String>();
		ArrayList<Double> itemCosts = new ArrayList<Double>();
		String fileName = textScanner.nextLine();
		try {
			CSVParser = new Scanner(new File("F:\\" + fileName + ".csv"));
			correctFile = true;
			CSVParser.useDelimiter(",");
			while(CSVParser.hasNext()) {
				switch(whichLine) {
					case 1: whichLine = 0;
						monthDuration = CSVParser.nextInt();
						whichLine++;
						break;
					case 2: whichLine = 1;
						String thisString = CSVParser.nextLine();						
					    Scanner sc = new Scanner(thisString);
					    name = sc.next();
					    initialSavings = Double.parseDouble(sc.next());
					    income = Double.parseDouble(sc.next());
					    whichLine++;
					    break;
					case 3: whichLine = 2;
						String thisBudgetItem = CSVParser.nextLine();						
						Scanner sc2 = new Scanner(thisBudgetItem);
						String itemName = sc2.next();
						double value = Double.parseDouble(sc2.next());
						itemNames.add(itemName);
						itemCosts.add(value);
						break;
				}
			}
			CSVParser.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found, please enter again.");
		}
		x
		Budget budget = new Budget(name, initialSavings, monthDuration, income);
		for(int i = 0; i < itemNames.size(); i++) {
			budget.addItem(new BudgetItem(itemNames.get(i), itemCosts.get(i)));
		}
		
	}
}
package BudgetAllocator;

import java.io.*;
import java.util.Scanner;

public class AllocMain {

	public static void main(String[] args) {
		boolean correctFile = false;

		Scanner textScanner = new Scanner(System.in);

		System.out.println("Please input your exactnCSV file name that is in this directory: ");
		Scanner CSVParser;
		String fileName = textScanner.nextLine();
		try {
			CSVParser = new Scanner(new File("F:\\" + fileName + ".csv"));
			correctFile = true;
			CSVParser.useDelimiter(",");
			while(CSVParser.hasNext()) {
				System.out.print(CSVParser.next());
			}
			CSVParser.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found, please enter again.");
		}

	}
}