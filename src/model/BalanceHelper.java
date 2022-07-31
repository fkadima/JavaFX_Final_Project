package model;

public class BalanceHelper {
	private String AccountNumber;
	private String AccountType;
	private Double Balance;
	
	public BalanceHelper() {
		super();
	}

	public BalanceHelper(String accountNumber, String accountType, Double balance) {
		super();
		AccountNumber = accountNumber;
		AccountType = accountType;
		Balance = balance;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public Double getBalance() {
		return Balance;
	}

	public void setBalance(Double balance) {
		Balance = balance;
	}
	

}
