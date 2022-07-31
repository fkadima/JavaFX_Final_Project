package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transaction {
	private String Id;
	private LocalDateTime TransactionDate;
	private String TransactionType;
	private String AccountNumber;
	private String AccountType;
	private Double Amount;
	private Double PreviousBalance;
	private Double NewBalance;
	
	public Transaction() {
		super();
	}
	
	

	public Transaction(String transactionType, String accountNumber,
			String accountType, Double amount, Double previousBalance, Double newBalance) {
		super();
		Id = UUID.randomUUID().toString();
		TransactionDate = LocalDateTime.now();
		TransactionType = transactionType;
		AccountNumber = accountNumber;
		AccountType = accountType;
		Amount = amount;
		PreviousBalance = previousBalance;
		NewBalance = newBalance;
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	public LocalDateTime getTransactionDate() {
		return TransactionDate;
	}
	
	public void setTransactionDate(LocalDateTime transactionDate) {
		TransactionDate = transactionDate;
	}
	
	public String getTransactionType() {
		return TransactionType;
	}
	
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	
	public Double getAmount() {
		return Amount;
	}
	
	public void setAmount(Double amount) {
		Amount = amount;
	}
	
	
	public Double getPreviousBalance() {
		return PreviousBalance;
	}

	public void setPreviousBalance(Double previousBalance) {
		PreviousBalance = previousBalance;
	}

	public Double getNewBalance() {
		return NewBalance;
	}

	public void setNewBalance(Double newBalance) {
		NewBalance = newBalance;
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
	
	
	@Override
	public String toString() {
		return "Transaction [Id=" + Id + ", TransactionDate=" + TransactionDate + ", TransactionType=" + TransactionType
				+ ", AccountNumber=" + AccountNumber + ", AccountType=" + AccountType + ", Amount=" + Amount
				+ ", PreviousBalance=" + PreviousBalance + ", NewBalance=" + NewBalance + "]";
	}

	public List<Transaction> accountTransactions(List<Transaction> transactions, String accountNumber) {
		List<Transaction> accountTransactions = new ArrayList<Transaction>();
		
		for (Transaction transaction : transactions) {
			if(transaction.getAccountNumber().equals(accountNumber)) {
				accountTransactions.add(transaction);
			}
		}
		
		return accountTransactions;
	}
	
	public User accountTransactions(User user) {
		return user;
	}
	
	public void deposit(List<Transaction> transactions, List<CheckingAccount> accounts, 
			String accountNumber,  Double amount) {
		CheckingAccount userAccount = new CheckingAccount();
		
		for (CheckingAccount account : accounts) {
			if(account.getAccountNumber().equals(accountNumber)) {
				userAccount = account;
			};
		}
		
		Double previousBalance = userAccount.getAccountBalance();
		Double newBalance =  previousBalance + amount;
		userAccount.setAccountBalance(newBalance);
		userAccount.setUpdateAt(LocalDateTime.now());
		userAccount.setAccessedOn(LocalDateTime.now());
		
		Transaction transaction = new Transaction("Deposit", userAccount.getAccountNumber(), "Checking", amount, previousBalance, newBalance);
		transactions.add(transaction);	
	}
	
	public CheckingAccount deposit(List<Transaction> transactions, User user, Double amount) {
		
		CheckingAccount userAccount = user.getUserCheckingAccount();
	
		Double previousBalance = userAccount.getAccountBalance();
		Double newBalance =  previousBalance + amount;
		userAccount.setAccountBalance(newBalance);
		userAccount.setUpdateAt(LocalDateTime.now());
		userAccount.setAccessedOn(LocalDateTime.now());
		
		Transaction transaction = new Transaction("Deposit", userAccount.getAccountNumber(), "Checking", amount, previousBalance, newBalance);
		transactions.add(transaction);	
		
		user.setUserTransactions(transactions); 
		
		return userAccount;
	}
	
	public void withdraw(List<Transaction> transactions, List<CheckingAccount> checkAccounts, 
			List<SavingsAccount> savAccounts, String accountNumber, Double amount) {
		CheckingAccount userCheckingAccount = new CheckingAccount();
		SavingsAccount userSavingAccount = new SavingsAccount();
		Transaction transaction;	
		
		for (CheckingAccount account : checkAccounts) {
			if(account.getAccountNumber().equals(accountNumber)) {
				userCheckingAccount = account;
			};
		}
		
		if(userCheckingAccount.getAccountNumber() == null) {
			for (SavingsAccount account : savAccounts) {
				if(account.getAccountNumber().equals(accountNumber)) {
					userSavingAccount = account;
				};
			}
		}
		
		if(userCheckingAccount.getAccountNumber() != null) {
			Double previousBalance = userCheckingAccount.getAccountBalance();
			Double newBalance =  previousBalance - amount;
			userCheckingAccount.setAccountBalance(newBalance);
			userCheckingAccount.setUpdateAt(LocalDateTime.now());
			userCheckingAccount.setAccessedOn(LocalDateTime.now());
			
			transaction = new Transaction("Withdraw", userCheckingAccount.getAccountNumber(), 
					"Checking", amount, previousBalance, newBalance);
			transactions.add(transaction);
			
		}
		
		if(userSavingAccount.getAccountNumber() != null) {
			Double previousBalance = userSavingAccount.getAccountBalance();
			Double newBalance =  previousBalance - amount;
			userSavingAccount.setAccountBalance(newBalance);
			userSavingAccount.setUpdateAt(LocalDateTime.now());
			userSavingAccount.setAccessedOn(LocalDateTime.now());
			
			transaction = new Transaction("Withdraw", userSavingAccount.getAccountNumber(), 
					"Savings", amount, previousBalance, newBalance);
			transactions.add(transaction);
		}
		
	}
	
	public Transaction withdraw(List<Transaction> transactions, User user, String accountNumber, Double amount) {
		
		CheckingAccount userCheckingAccount = user.getUserCheckingAccount();
		SavingsAccount userSavingAccount = user.getUserSavingsAccount();
		Transaction transaction = new Transaction();	
		
		
		if(userCheckingAccount.getAccountNumber().equals(accountNumber)) {
			Double previousBalance = userCheckingAccount.getAccountBalance();
			Double newBalance =  previousBalance - amount;
			userCheckingAccount.setAccountBalance(newBalance);
			userCheckingAccount.setUpdateAt(LocalDateTime.now());
			userCheckingAccount.setAccessedOn(LocalDateTime.now());
			
			transaction = new Transaction("Withdraw", userCheckingAccount.getAccountNumber(), 
					"Checking", amount, previousBalance, newBalance);
			transactions.add(transaction);
			
		}
		
		if(userSavingAccount.getAccountNumber().equals(accountNumber)) {
			Double previousBalance = userSavingAccount.getAccountBalance();
			Double newBalance =  previousBalance - amount;
			userSavingAccount.setAccountBalance(newBalance);
			userSavingAccount.setUpdateAt(LocalDateTime.now());
			userSavingAccount.setAccessedOn(LocalDateTime.now());
			
			transaction = new Transaction("Withdraw", userSavingAccount.getAccountNumber(), 
					"Savings", amount, previousBalance, newBalance);
			transactions.add(transaction);
		}
		
		return transaction;
		
	}
	
	public BalanceHelper balance(List<SavingsAccount> savingsAccounts, List<CheckingAccount> checkingAccounts, String accountNumber) {
		CheckingAccount userCheckingAccount = new CheckingAccount();
		SavingsAccount userSavingAccount = new SavingsAccount();
		double balance = 0.0;
		String accountType = "";
		
		for (CheckingAccount account : checkingAccounts) {
			if(account.getAccountNumber().equals(accountNumber)) {
				userCheckingAccount = account;
				if(userCheckingAccount != null) {
					balance = account.getAccountBalance();
					accountType = "Checking";
				}				
			};
		} 
		
		if(userCheckingAccount.getAccountNumber() == null ) {
			for (SavingsAccount account : savingsAccounts) {
				if(account.getAccountNumber().equals(accountNumber)) {
					userSavingAccount = account;
					balance = account.getAccountBalance();
					accountType = "Savings";
				}
			}
		}
		
		BalanceHelper balanceHelper = new BalanceHelper(accountNumber, accountType, balance);
		
		return balanceHelper;
		
	}
	
	public void tranferFromCheckingToSaving(List<Transaction> transactions, List<CheckingAccount> checkAccounts, 
			List<SavingsAccount> savAccounts, String fromAccount, String toAccount, Double amount) {
		
		CheckingAccount userCheckingAccount = new CheckingAccount();
		SavingsAccount userSavingsAccount = new SavingsAccount();
		
		
		for (CheckingAccount account : checkAccounts) {
			if(account.getAccountNumber().equals(fromAccount)) {
				userCheckingAccount = account;
			}
			else {
				System.out.println("Wrong Checking Account Provided. Please Try Again!");
			}
		}
		
		for (SavingsAccount account : savAccounts) {
			if(account.getAccountNumber().equals(toAccount)) {
				userSavingsAccount = account;
			}
			else {
				System.out.println("Wrong Savings Account Provided. Please Try Again!");
			}
		}
		
		if(userCheckingAccount.getAccountNumber() != null && userSavingsAccount.getAccountNumber() != null) {
			Double previousCheckingBalance = userCheckingAccount.getAccountBalance();
			Double currentCheckingBalance = previousCheckingBalance - amount;
			
			Double previousSavingsBalance = userSavingsAccount.getAccountBalance();
			Double currentSavingsBalance = previousSavingsBalance + amount;
			
			Transaction transaction1 = new Transaction("Transfer", userCheckingAccount.getAccountNumber(), "Checking", amount, previousCheckingBalance, currentCheckingBalance);
			transactions.add(transaction1);
			
			Transaction transaction2 = new Transaction("Transfer", userSavingsAccount.getAccountNumber(), "Savings", amount, previousSavingsBalance, currentSavingsBalance);
			transactions.add(transaction2);

			System.out.println("Successfully Tranferred $" + amount + " from Checking to Savings Account.");
		}
			
	}
	
	public User tranferFromCheckingToSaving(List<Transaction> transactions, User user, Double amount) {
		
		CheckingAccount userCheckingAccount = user.getUserCheckingAccount();
		SavingsAccount userSavingsAccount = user.getUserSavingsAccount();
						
		if(user.getUserCheckingAccount().getAccountNumber() != null && userSavingsAccount.getAccountNumber() != null) {
			Double previousCheckingBalance = userCheckingAccount.getAccountBalance();
			Double currentCheckingBalance = previousCheckingBalance - amount;
			
			Double previousSavingsBalance = userSavingsAccount.getAccountBalance();
			Double currentSavingsBalance = previousSavingsBalance + amount;
			
			Transaction transaction1 = new Transaction("Transfer", userCheckingAccount.getAccountNumber(), "Checking", amount, previousCheckingBalance, currentCheckingBalance);
			transactions.add(transaction1);
			userCheckingAccount.setAccountBalance(currentCheckingBalance);
			
			Transaction transaction2 = new Transaction("Transfer", userSavingsAccount.getAccountNumber(), "Savings", amount, previousSavingsBalance, currentSavingsBalance);
			transactions.add(transaction2);
			userSavingsAccount.setAccountBalance(currentSavingsBalance);

			System.out.println("Successfully Tranferred $" + amount + " from Checking to Savings Account.");
		}
		
		return user;
			
	}
	
	public User tranferFromSavingToChecking(List<Transaction> transactions, User user, Double amount) {
		
		CheckingAccount userCheckingAccount = user.getUserCheckingAccount();
		SavingsAccount userSavingsAccount = user.getUserSavingsAccount();
		
		if(userCheckingAccount.getAccountNumber() != null && userSavingsAccount.getAccountNumber() != null) {
			
			Double previousSavingsBalance = userSavingsAccount.getAccountBalance();
			Double currentSavingsBalance = previousSavingsBalance - amount;
			
			Double previousCheckingBalance = userCheckingAccount.getAccountBalance();
			Double currentCheckingBalance = previousCheckingBalance + amount;			
			
			Transaction transaction1 = new Transaction("Transfer", userCheckingAccount.getAccountNumber(), "Checking", amount, previousCheckingBalance, currentCheckingBalance);
			transactions.add(transaction1);
			userCheckingAccount.setAccountBalance(currentCheckingBalance);
			
			Transaction transaction2 = new Transaction("Transfer", userSavingsAccount.getAccountNumber(), "Savings", amount, previousSavingsBalance, currentSavingsBalance);
			transactions.add(transaction2);
			userSavingsAccount.setAccountBalance(currentSavingsBalance);

			System.out.println("Successfully Tranferred $" + amount + " from Savings to Checking Account.");
		}
		
		return user;
			
	}
	
	public void tranferFromSavingToChecking(List<Transaction> transactions, List<CheckingAccount> checkAccounts, 
			List<SavingsAccount> savAccounts, String fromAccount, String toAccount, Double amount) {
		
		CheckingAccount userCheckingAccount = new CheckingAccount();
		SavingsAccount userSavingsAccount = new SavingsAccount();
		
		for (SavingsAccount account : savAccounts) {
			if(account.getAccountNumber().equals(fromAccount)) {
				userSavingsAccount = account;
			}
			else {
				System.out.println("Wrong Savings Account Provided. Please Try Again!");
			}
		}
		
		for (CheckingAccount account : checkAccounts) {
			if(account.getAccountNumber().equals(toAccount)) {
				userCheckingAccount = account;
			}
			else {
				System.out.println("Wrong Checking Account Provided. Please Try Again!");
			}
		}
			
		
		if(userCheckingAccount.getAccountNumber() != null && userSavingsAccount.getAccountNumber() != null) {
			
			Double previousSavingsBalance = userSavingsAccount.getAccountBalance();
			Double currentSavingsBalance = previousSavingsBalance - amount;
			
			Double previousCheckingBalance = userCheckingAccount.getAccountBalance();
			Double currentCheckingBalance = previousCheckingBalance + amount;			
			
			Transaction transaction1 = new Transaction("Transfer", userCheckingAccount.getAccountNumber(), "Checking", amount, previousCheckingBalance, currentCheckingBalance);
			transactions.add(transaction1);
			
			Transaction transaction2 = new Transaction("Transfer", userSavingsAccount.getAccountNumber(), "Savings", amount, previousSavingsBalance, currentSavingsBalance);
			transactions.add(transaction2);

			System.out.println("Successfully Tranferred $" + amount + " from Savings to Checking Account.");
		}
			
	}

}
