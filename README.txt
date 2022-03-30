# project-atassi-oishi-wilson
project-atassi-oishi-wilson created by GitHub Classroom

Budget Allocator 
Ferris Atassi, Jasper Wilson, Benn Oishi

We have created a basic skeleton for how our budget allocator will be implemented. This will help us organize our code early on so there are hopefully less errors and a more readable, optimized product.
As of currently, a user can give the BudgetAllocator a path to their CSV file to have it read. For our next steps we plan to use this raw information of the input CSV file to create BudgetItem and Budget objects for the user to accurately represent their expenses. 

At the moment, our project is able to parse a simple CSV file. In the next iteration, we plan to use the parsed values of the input CSV file to create BudgetItem and Budget objects for the user to accurately represent their expenses and print them to the user so they can be visualized as well. 

To compile our program from the command line, open the terminal/console and navigate to the src file. Type: "javac BudgetAllocator.AllocMain.java". Then, "java BudgetAllocator.AllocMain" should run the program. A simple script (script.js) would be:
javac BudgetAllocator.AllocMain.java
java BudgetAllocator.AllocMain
