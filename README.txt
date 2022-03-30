# project-atassi-oishi-wilson
project-atassi-oishi-wilson created by GitHub Classroom

Budget Allocator 
Ferris Atassi, Jasper Wilson, Benn Oishi

We have created a basic skeleton for how our budget allocator will be implemented. This will help us organize our code early on so there are hopefully less errors and a more readable, optimized product.
As of currently, a user can give the BudgetAllocator a path to my CSV file and have it read. For our next steps we plan to use this scraped information of the input CSV file to create BudgetItem and Budget objects for the user to accurately represent their expenses. 

At the moment, all our project is able to do is parse a simple CSV file. In the next iteration, we will add functional code to several of our classes that will implement numerous features.
As a user, I can give the BudgetAllocator a path to my CSV file so it can read and display numerous values pertaining to my budget. Next we plan to use this scraped information of the input CSV file to create BudgetItem and Budget objects for the user to accurately represent their expenses and print them to the user so that they can be visualized as well. As of currently we have the skeleton for the implementation of print the CSV data, but by next iteration it will be printed in the cmd line, available for user analysis.

To compile our program from the command line, navigate to the src file and type: "javac BudgetAllocator.AllocMain.java". Then, "java BudgetAllocator.AllocMain" should run the program. A simple script (script.js) would be:
javac BudgetAllocator.AllocMain.java
java BudgetAllocator.AllocMain
