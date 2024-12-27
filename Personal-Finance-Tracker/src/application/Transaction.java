package application;

public class Transaction {
	private int id;
	private String type;
	private double amount;
	private String date;
	private String description;

	public Transaction() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Transaction(int id, String type, double amount, String date, String description) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", type=" + type + ", amount=" + amount + ", date=" + date + ", description="
				+ description + "]";
	}

	public String getDetails() {
		return "\nTransaction Id : " + id + "\nType : " + type + "\nAmount : " + amount + "\nDescription : "
				+ description;
	}

}
