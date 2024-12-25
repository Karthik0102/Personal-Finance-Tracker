package application;

import java.util.ArrayList;
import java.util.List;

public class FinanceManager {

	private List<Transaction> transactions;
	private int transactionCounter = 1;

	public FinanceManager() {
		this.transactions = new ArrayList<Transaction>();
	}

	public void addTransaction(String type, double amount, String date, String description) {
		try {
			if (type.equals("income") | type.equals("expense")) {
				Transaction transaction = new Transaction(transactionCounter, type, amount, date, description);
				transactions.add(transaction);
				transactionCounter++;
				System.out.println("Txn added successfully");
			} else {
				System.out.println("Invalid Transaction Type. Must be 'income' or 'expense'.");
			}
		} catch (Exception e) {
			System.out.println("Error while adding txns " + e.getStackTrace());
		}
	}

	public void viewTransactions() {
		if (!transactions.isEmpty()) {
			for (Transaction transaction : transactions) {
				System.out.println("Transaction Details : " + transaction.getDetails());
			}
		} else {
			System.out.println("No transactions");
		}
	}

	public double getTotalByType(String type) {
		int totalAmount = 0;
		for (Transaction transaction : transactions) {
			if (transaction.getType().equals(type)) {
				totalAmount += transaction.getAmount();
			}
		}
		return totalAmount;
	}

	public double getBalance() {
		double balance;
		double totalIncome = getTotalByType("income");
		double totalExpense = getTotalByType("expense");
		balance = totalIncome - totalExpense;
		return balance;
	}
}
