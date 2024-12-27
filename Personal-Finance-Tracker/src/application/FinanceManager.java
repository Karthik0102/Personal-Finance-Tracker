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
			Transaction transaction = new Transaction(transactionCounter, type, amount, date, description);
			transactions.add(transaction);
			transactionCounter++;
			System.out.println("Transaction added successfully!!");
		} catch (Exception e) {
			System.out.println("Error while adding txns " + e.getStackTrace());
		}
	}

	// view txns with for Each and Method Reference
	public void viewTransactions() {
		if (!transactions.isEmpty()) {
			transactions.forEach(System.out::println);
		} else {
			System.out.println("No transactions");
		}
	}

	// calculate total by type using Stream API
	public double getTotalByType(String type) {
		return transactions.stream().filter(transaction -> transaction.getType().equals(type))
				.mapToDouble(Transaction::getAmount).sum();
	}

	public double getBalance() {
		double totalIncome = getTotalByType("income");
		double totalExpense = getTotalByType("expense");
		return totalIncome - totalExpense;
	}
}
