package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FinanceManager {

	private List<Transaction> transactions;
	private int transactionCounter = 1;

	public FinanceManager() {
		this.transactions = new ArrayList<Transaction>();
	}

	public void addTransaction(String type, double amount, String date, String description,
			TransactionCategory category) {
		try {
			Transaction transaction = new Transaction(transactionCounter, type, amount, date, description, category);
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
			transactions.stream().forEach(System.out::println);
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

	public void viewTransactionsByCategory(TransactionCategory category) {
		transactions.stream().filter(transaction -> transaction.getCategory().equals(category))
				.forEach(System.out::println);
	}

	public Optional<Transaction> getTransactionById(int id) {
		return transactions.stream().filter(transaction -> transaction.getId() == id).findFirst(); // Returns
																									// Optional<Transaction>
	}

	public double calculateBalance() {
		double balance = 0.0;
		for (Transaction transaction : transactions) {
			if (transaction.getType().equalsIgnoreCase("income")) {
				balance += transaction.getAmount();
			} else if (transaction.getType().equalsIgnoreCase("expense")) {
				balance -= transaction.getAmount();
			}
		}
		return balance;
	}

	public void saveTransactions(String fileName) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			for (Transaction transaction : transactions) {
				writer.write(transaction.toString());
				writer.newLine();
			}
			System.out.println("Transactions saved to file: " + fileName);
		} catch (IOException e) {
			System.out.println("Error saving transactions: " + e.getStackTrace());
		}
	}

	public void sortTransactionsByDate() {
		if (!transactions.isEmpty()) {
			transactions.sort(Comparator.comparing(Transaction::getDate));
			System.out.println("Transactions sorted by date.");
		} else {
			System.out.println("No Transactions.");
		}
	}

	public void loadTransactionsFromFile(String fileName) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			transactions.clear();
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("Transactions loaded from file: " + fileName);
		} catch (IOException e) {
			System.out.println("Error loading transactions: " + e.getMessage());
		}
	}

	public void removeTransaction(int id) {
		Transaction toRemove = transactions.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		if (toRemove != null) {
			transactions.remove(toRemove);
			System.out.println("Transaction removed successfully!");
		} else {
			System.out.println("Transaction with ID " + id + " not found.");
		}

	}
}
