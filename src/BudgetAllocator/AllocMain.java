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
