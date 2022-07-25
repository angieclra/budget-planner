package ui;

import model.BudgetBookEntries;
import model.Entry;

import java.util.Locale;
import java.util.Scanner;

// Budget Book Planner Application
public class BudgetApp {

    private BudgetBookEntries entries;
    private Scanner sc;

    // EFFECTS: runs the Budget Book Planner Application
    public BudgetApp() {
        entries = new BudgetBookEntries("Angelique's Budget Book");
        runApp();
    }

    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: processes user input
    public void runApp() {
        boolean keepGoing = true;
        String command = null;

        sc = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = sc.next();

            if (command.equals("5")) {
                keepGoing = false;
                doPrintInvoice();
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you again, thank you!");
    }

    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {

        if (command.equals("1")) {
            doAddEntryToBudgetBook();
        } else if (command.equals("2")) {
            doRemoveFromBudgetBook();
        } else if (command.equals("3")) {
            doTotalItemsInBudgetBook();
        } else if (command.equals("4")) {
            doTotalMoneySpent();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add spending to budget book");
        System.out.println("\t2 -> Remove spending from budget book");
        System.out.println("\t3 -> Total number of items in budget book");
        System.out.println("\t4 -> Total money spent");
        System.out.println("\t5 -> Quit budget app");
    }

    // MODIFIES: this
    // EFFECTS: asks for user input for the
    // spending's name, spending's category, and money spent on the spending
    private void doAddEntryToBudgetBook() {
        String name;
        String category;
        double moneySpent;
        Entry entry1;

        Scanner sc = new Scanner(System.in);

        System.out.println("What is the name of the item spent?");
        name = sc.next();

        System.out.println("What category does this spending belongs to?");
        category = sc.next();

        System.out.println("How much money is spent on the item?");
        moneySpent = sc.nextDouble();

        entry1 = new Entry(name, category, moneySpent);
        entries.addToBudgetEntries(entry1);
        System.out.println("Category: " + category + " \n" + "Name: " + name + "\n" + "Money Spent: " + moneySpent
                + "\n" + "successfully added to budget book.");

    }

    // MODIFIES: this
    // EFFECTS: asks for the user input (yes/ no) if user wants to remove spending from budget book
    // if yes, ask for user input of the spending name, and remove the spending picked by user from the budget book
    // if no, display menu to allow user to pick other options
    // if else, ask user to pick between yes or no
    private void doRemoveFromBudgetBook() {
        String answer;

        System.out.println("Are you sure to remove a spending from the budget book? (y/n)");
        answer = sc.next();

        if (answer.equals("y")) {
            System.out.println("Pick the spending you want to remove from the budget book: ");
            answer = sc.next();
            entries.removeFromBudgetEntries(answer);
        } else if (answer.equals("n")) {
            System.out.println("Please pick another option to proceed.");
            displayMenu();
        } else {
            System.out.println("Selection not valid, please enter valid options.");
        }
    }

    // MODIFIES: this
    // EFFECTS: prints the total items in the user's budget book
    private void doTotalItemsInBudgetBook() {
        System.out.println("\n" + "Total spending in your budget book is: " + entries.getNumEntries());
    }

    // MODIFIES: this
    // EFFECTS: prints the total money spent by user, by calculating the total from the user's budget book
    private void doTotalMoneySpent() {
        System.out.println("\n" + "Total money spent: " + entries.getMoneySpentTotal());
    }

    // MODIFIES: this
    // EFFECTS: prints the invoice for the user along with the name, the category, and money spent of the spending,
    // and the total money spent in the budget book
    private void doPrintInvoice() {
        System.out.println("\n" + entries.printInvoice() + "\nTotal: $" + entries.getMoneySpentTotal());
    }

}


