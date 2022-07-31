package model;

import java.util.ArrayList;
import java.util.List;

public class Functionality {
	
	String errorMessage = "";

	List<User> users = new ArrayList<User>();
	List<CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();
	List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
	
	public Functionality() {
		initialization();
	}

	public void initialization() {
		User user1 = new User("Fabrice", "Kadima", "E", "fkadima@test.com", "101 Main St", "Jeffersonville", "Indiana",
				"47130", "testpass");

		User user2 = new User("Therese", "Kadima", "M", "tkadima@test.com", "101 Main St", "Jeffersonville", "Indiana", "47130",
				"testpass");
		
		User user3 = new User("YedidYah", "Kadima", "M", "yedidyah@test.com", "101 Main St", "Jeffersonville", "Indiana", "47130",
				"testpass");
		
		User user4 = new User("Elisheba", "Kadima", "K", "elisheba@test.com", "101 Main St", "Jeffersonville", "Indiana", "47130",
				"testpass");
		
		User user5 = new User("Hadassah", "Kadima", "M", "hadassah@test.com", "101 Main St", "Jeffersonville", "Indiana", "47130",
				"testpass");
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		
		CheckingAccount user1CheckingAccount = new CheckingAccount(user1.getId(), "1014444555566", 100.00);		
		SavingsAccount user1SavingsAccount = new SavingsAccount(user1.getId(), "1024444555566", 180.00);
				
		CheckingAccount user2CheckingAccount = new CheckingAccount(user2.getId(), "1014444558898", 120.00);		
		SavingsAccount user2SavingsAccount = new SavingsAccount(user2.getId(), "1024444558898", 140.00);
		
		CheckingAccount user3CheckingAccount = new CheckingAccount(user3.getId(), "1014444558523", 120.00);		
		SavingsAccount user3SavingsAccount = new SavingsAccount(user3.getId(), "1024444558523", 140.00);
		
		CheckingAccount user4CheckingAccount = new CheckingAccount(user4.getId(), "1014444554785", 120.00);		
		SavingsAccount user4SavingsAccount = new SavingsAccount(user4.getId(), "1024444554785", 140.00);
		
		CheckingAccount user5CheckingAccount = new CheckingAccount(user5.getId(), "1014444559632", 120.00);		
		SavingsAccount user5SavingsAccount = new SavingsAccount(user5.getId(), "1024444559632", 140.00);		
		
		checkingAccounts.add(user1CheckingAccount);
		savingsAccounts.add(user1SavingsAccount);
		
		checkingAccounts.add(user2CheckingAccount);
		savingsAccounts.add(user2SavingsAccount);
		
		checkingAccounts.add(user3CheckingAccount);
		savingsAccounts.add(user3SavingsAccount);
		
		checkingAccounts.add(user4CheckingAccount);
		savingsAccounts.add(user4SavingsAccount);
		
		checkingAccounts.add(user5CheckingAccount);
		savingsAccounts.add(user5SavingsAccount);
		
	}
	
	public User login(String email, String password) {
		
		for (User user : users) {
			// Log the user only if the email AND password are correct
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				
				for (CheckingAccount checking : checkingAccounts) {
					if(checking.getUserId().equals(user.getId())) {
						user.setUserCheckingAccount(checking);
					}
				}
				
				for (SavingsAccount saving : savingsAccounts) {
					if(saving.getUserId().equals(user.getId())) {
						user.setUserSavingsAccount(saving);
					}
				}
				
				CheckingAccount check = user.getUserCheckingAccount();
				
				List<Transaction> transactions = new ArrayList<Transaction>();
				
				Transaction transaction = new Transaction("Deposit", check.getAccountNumber(), "Checking", check.getAccountBalance(), check.getAccountBalance(), check.getAccountBalance());
				transactions.add(transaction);
				user.setUserTransactions(transactions);
				
				return user;
			}
		}
		return null;
	}
}
