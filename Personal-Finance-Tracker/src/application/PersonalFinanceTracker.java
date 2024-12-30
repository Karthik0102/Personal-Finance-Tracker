package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PersonalFinanceTracker {

	private static final Scanner sc = new Scanner(System.in);
	private static FinanceManager financeManager = new FinanceManager();

	private static void displayMenu() {
		System.out.println("\n----- Personal Finance Tracker -----");
		System.out.println("1. Add Transaction");
		System.out.println("2. View Transactions");
		System.out.println("3. View Balance");
		System.out.println("4. Calculate Balance in Background");
		System.out.println("5. Save Transactions");
		System.out.println("6. Load Transactions");
		System.out.println("7. Remove Transaction");
		System.out.println("8. Sort Transactions");
		System.out.println("9. View Total Income");
		System.out.println("10. View Total Expenses");
		System.out.println("11. View Transactions by Category");
		System.out.println("12. Exit");
		System.out.print("Enter your choice: ");
	}

	private static int getValidMenuChoice() {
		while (!sc.hasNextInt()) {
			System.out.println("Invalid input. Please enter a number between 1 and 11.");
			sc.next();
		}
		int choice = sc.nextInt();
		sc.nextLine(); // Consume newline character
		return choice;
	}

	private static void addTransaction() {
		String type;
		while (true) {
			System.out.println("Enter transaction type (income/expense): ");
			type = sc.nextLine().trim().toLowerCase();
			if (InputValidator.isValidTransctionType(type)) {
				break;
			} else {
				System.out.println("Invalid transaction type. Must be 'income' or 'expense'.");
			}
		}

		double amount;
		while (true) {
			System.out.println("Enter Amount: ");
			if (sc.hasNextDouble()) {
				amount = sc.nextDouble();
				sc.nextLine(); // Consume newline character
				if (InputValidator.isValidAmount(amount)) {
					break;
				} else {
					System.out.println("Invalid amount. It must be greater than 0.");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid number.");
				sc.nextLine(); // Consume invalid input
			}
		}

		String date;
		while (true) {
			System.out.println("Enter Date (YYYY-MM-DD): ");
			date = sc.nextLine().trim();
			if (InputValidator.isValidDate(date)) {
				break;
			} else {
				System.out.println("Invalid date format. Please use 'YYYY-MM-DD'.");
			}
		}

		String description;
		while (true) {
			System.out.println("Enter Description: ");
			description = sc.nextLine().trim();
			if (InputValidator.isValidDescription(description)) {
				break;
			} else {
				System.out.println("Description cannot be empty.");
			}
		}

		String category;
		TransactionCategory transactionCategory;
		while (true) {
			System.out.println("Enter Transaction Category (e.g., Food, Bills, Entertainment, Salary): ");
			category = sc.nextLine().trim().toUpperCase();
			try {
				transactionCategory = TransactionCategory.valueOf(category);
				break; // Exit the loop if a valid category is entered
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid category. Please choose a valid category.");
				System.out.println("Valid categories are: " + Arrays.toString(TransactionCategory.values()));
			}
		}

		financeManager.addTransaction(type, amount, date, description, transactionCategory);
	}

	private static void calculateBalanceInBackground() {
		Thread balanceThread = new Thread(() -> {
			double balance = financeManager.calculateBalance();
			System.out.println("Background balance calculation completed: " + balance);
		});
		balanceThread.start();
	}

	private static void saveTransactions() throws IOException {
		System.out.println("Enter file name to save transactions: ");
		String fileName = sc.nextLine().trim();
		if (fileName.isEmpty()) {
			System.out.println("File name cannot be empty. Please provide a valid name.");
			return;
		}
		financeManager.saveTransactions(fileName);
	}

	private static void loadTransactions() throws IOException {
		System.out.println("Enter file name to load transactions: ");
		String fileName = sc.nextLine().trim();
		financeManager.loadTransactionsFromFile(fileName);
	}

	private static void removeTransaction() {
		System.out.println("Enter transaction ID to remove: ");
		while (!sc.hasNextInt()) {
			System.out.println("Invalid input. Please enter a valid transaction ID.");
			sc.next();
		}
		int id = sc.nextInt();
		sc.nextLine(); // Consume newline
		financeManager.removeTransaction(id);
		System.out.println("Transaction removed successfully.");
	}

	private static void handleViewBalance() {
		double balance = financeManager.getBalance();
		System.out.println("Current Balance: " + balance);
	}

	private static void handleViewTotalIncome() {
		double totalIncome = financeManager.getTotalByType("income");
		System.out.println("Total Income: " + totalIncome);
	}

	private static void handleViewTotalExpenses() {
		double totalExpenses = financeManager.getTotalByType("expense");
		System.out.println("Total Expenses: " + totalExpenses);
	}

	private static void handleViewTransactionsByCategory() {
		System.out.println("Enter transaction category (e.g., FOOD, BILLS, ENTERTAINMENT, SALARY): ");
		String category = sc.nextLine().trim().toUpperCase();
		try {
			TransactionCategory transactionCategory = TransactionCategory.valueOf(category);
			financeManager.viewTransactionsByCategory(transactionCategory);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid category. Please choose a valid category.");
		}
	}

	public static void main(String[] args) throws IOException {
		while (true) {
			displayMenu();
			int choice = getValidMenuChoice();

			switch (choice) {
			case 1:
				addTransaction();
				break;
			case 2:
				financeManager.viewTransactions();
				break;
			case 3:
				handleViewBalance();
				break;
			case 4:
				calculateBalanceInBackground();
				break;
			case 5:
				saveTransactions();
				break;
			case 6:
				loadTransactions();
				break;
			case 7:
				removeTransaction();
				break;
			case 8:
				financeManager.sortTransactionsByDate();
				break;
			case 9:
				handleViewTotalIncome();
				break;
			case 10:
				handleViewTotalExpenses();
				break;
			case 11:
				handleViewTransactionsByCategory();
				break;
			case 12:
				System.out.println("Exiting the application. Goodbye!");
				sc.close();
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

}
