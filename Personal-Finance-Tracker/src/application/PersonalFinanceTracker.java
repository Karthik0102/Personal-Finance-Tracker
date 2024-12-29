package application;

import java.util.Scanner;

public class PersonalFinanceTracker {

	private FinanceManager financeManager;

	public PersonalFinanceTracker() {
		financeManager = new FinanceManager();
	}

	public void mainMenu() {
		System.out.println("\n Personal Finance Tracker - Main Menu");
		System.out.println("1. Add Transaction");
		System.out.println("2. View Transactions");
		System.out.println("3. View Total Income");
		System.out.println("4. View Total Expenses");
		System.out.println("5. View Balance");
		System.out.println("6. Exit");
		System.out.print("Choose An Option: ");
	}

	public void handleAddTransaction(Scanner sc) {
		// Validate transaction type
		String type = "";
		while (true) {
			System.out.println("Enter transaction type (income/expense):: ");
			type = sc.nextLine().toLowerCase();

			if (InputValidator.isValidTransctionType(type)) {
				break; // Exit the loop if the input is valid
			} else {
				System.out.println("Invalid transaction type, Must be 'income' or 'expense'.");
			}
		}

		// Validate amount (must be greater than 0)
		double amount = -1;
		while (true) {
			System.out.println("Enter amount: ");
			if (sc.hasNextDouble()) {
				amount = sc.nextDouble();
				sc.nextLine();
				if (InputValidator.isValidAmount(amount)) {
					break;
				} else {
					System.out.println("Invalid Amount. Must be greater than 0.");
				}
			} else {
				System.out.println("Invalid amount. Please enter a valid number.");
				sc.nextLine();
			}
		}

		// Validate date format (YYYY-MM-DD)
		String date = "";
		while (true) {
			System.out.println("Enter Date (YYYY-MM-DD): ");
			date = sc.nextLine();
			if (InputValidator.isValidDate(date)) {
				break; // Exit the loop if the date is valid
			} else {
				System.out.println("Invalid date format. Please enter in correct format!");
			}
		}

		// Validate description (must not be empty)
		String description = "";
		while (true) {
			System.out.println("Enter Description: ");
			description = sc.nextLine();
			if (InputValidator.isValidDescription(description)) {
				break; // Exit the loop if description is valid
			} else {
				System.out.println("Description cannot be empty.");
			}
		}

		TransactionCategory category = null;
		while (true) {
			System.out.println("Enter Transaction Category (e.g., Food, Bills, Entertainment):");
			String categoryInput = sc.nextLine().toUpperCase();

			try {
				category = TransactionCategory.valueOf(categoryInput);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid category. Please choose a valid category.");
			}
		}

		// If all validations pass, add the transaction
		financeManager.addTransaction(type, amount, date, description, category);
	}

	public void handleViewTransactions() {
		financeManager.viewTransactions();
	}

	public void handleViewTotalIncome() {
		double totalIncome = financeManager.getTotalByType("income");
		System.out.println("Total Income: " + totalIncome);
	}

	public void handleViewTotalExpenses() {
		double totalExpenses = financeManager.getTotalByType("expense");
		System.out.println("Total Expense: " + totalExpenses);
	}

	public void handleViewBalance() {
		double balance = financeManager.getBalance();
		System.out.println("Current Balance: " + balance);
	}

	public static void main(String[] args) {
		PersonalFinanceTracker tracker = new PersonalFinanceTracker();
		Scanner sc = new Scanner(System.in); // Create a single Scanner object

		while (true) {
			tracker.mainMenu();

			int choice = -1;
			// Handling invalid input for menu choices
			try {
				if (sc.hasNextInt()) {
					choice = sc.nextInt();
					sc.nextLine(); // Consume the newline character after reading an integer
				} else {
					throw new Exception("Invalid input");
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a valid number.");
				sc.nextLine(); // Clear the invalid input
				continue; // Continue the loop to prompt for input again
			}

			switch (choice) {
			case 1:
				tracker.handleAddTransaction(sc);
				break;
			case 2:
				tracker.handleViewTransactions();
				break;
			case 3:
				tracker.handleViewTotalIncome();
				break;
			case 4:
				tracker.handleViewTotalExpenses();
				break;
			case 5:
				tracker.handleViewBalance();
				break;
			case 6:
				System.out.println("Exiting application...");
				sc.close(); // Closing the scanner at the end of the program
				return;
			default:
				System.out.println("Invalid choice, please try again.");
			}
		}
	}
}
