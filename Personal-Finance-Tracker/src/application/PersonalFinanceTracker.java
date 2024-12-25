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
		System.out.println("Choose An Option");
	}

	public void handleAddTransaction() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter transaction type (income/expense):: ");
		String type = sc.nextLine().toLowerCase();

		System.out.println("Enter amount: ");
		double amount = sc.nextDouble();
		sc.nextLine();

		System.out.println("Enter Date (YYYY-MM-DD): ");
		String date = sc.nextLine();

		System.out.println("Enter Description: ");
		String description = sc.nextLine();

		financeManager.addTransaction(type, amount, date, description);
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
		Scanner sc = new Scanner(System.in);

		while (true) {
			tracker.mainMenu();
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				tracker.handleAddTransaction();
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
				sc.close();
				return;
			default:
				System.out.println("Invalid choice, please try again.");
			}

		}

	}

}
