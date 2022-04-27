# project-atassi-oishi-wilson
project-atassi-oishi-wilson created by GitHub Classroom

Budget Allocator 
Ferris Atassi, Jasper Wilson, Benn Oishi

In this iteration, we focused on fleshing out the functionality of our application along with adding more complexity to what we already have. We fixed income items to be represenstations of negative budget items. We refactored much of our code to follow the guidelines mentioned in class and also made sure to get rid of anything that should not be staged in our git repository. We added a feature taking into account priorities for specific budget items to enable user customization when they go over budget and need to remove an item. Our project now correctly does removal, so users are fully able to edit their budget month by month. Also worked on the user story regarding not having everything in main as we refactored it into a new class called menu to make main consist of more method calls than raw code. 

To run code, open project cd into src and run "sh ./script.sh".

Instructions to run: Go into parent directory of project, (where src and budget allocator), and type java BudgetAllocator/AllocMain, for the inputted file name type "TestFile" exactly or it will not work.


To compile our program from the command line, open the terminal/console and navigate to the src file. Type: "javac BudgetAllocator.AllocMain.java". Then, "java BudgetAllocator.AllocMain" should run the program. A simple script (script.js) would be:
javac BudgetAllocator.AllocMain.java
java BudgetAllocator.AllocMain
