package testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.BalanceHelper;
import model.CheckingAccount;
import model.Functionality;
import model.SavingsAccount;
import model.Transaction;
import model.User;

public class ATMTestClass {

	public static void main(String[] args) {
		
		Functionality functionality = new Functionality();
		
		User user = functionality.login("hadassah@test.com", "testpass");
		
		System.out.println(user);
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		
		Integer userCount = 1;
		Integer transactionCount = 1;
		List<User> users = new ArrayList<User>();
		List<CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();
		List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		/*Testing User Functionality*/
		/*Creating users*/
		System.out.println("==================== ATM Users ====================");
		User user1 = new User("John", "Smith", "T", "jsmith@test.com", "101 Main St", "Jeffersonville", "Indiana", "47130", "testpass");
		users.add(user1);
		
		User user2 = new User("John", "Doe", "C", "jdoe@test.com", "101 Main St", "Jeffersonville", "Indiana", "47130", "testpass");
		users.add(user2);
		
//		for (User user : users) {
//			System.out.println("User " + userCount + " : " + user.toString());
//			userCount++;
//		}
		
		System.out.println();
		/*Creating Checking Account*/
		/*User 1 Accounts*/
		System.out.println("==================== ATM User Accounts ====================");
		CheckingAccount user1CheckingAccount = new CheckingAccount(user1.getId(), "1014444555566", 100.00);
		checkingAccounts.add(user1CheckingAccount);
		System.out.println("User 1 Checking Account: " + user1CheckingAccount);
		
		SavingsAccount user1SavingsAccount = new SavingsAccount(user1.getId(), "1024444555566", 180.00);
		savingsAccounts.add(user1SavingsAccount);
		System.out.println("User 1 Savings Account: " + user1SavingsAccount);
		
		/*User 2 Accounts*/
		CheckingAccount user2CheckingAccount = new CheckingAccount(user2.getId(), "1014444558898", 120.00);
		checkingAccounts.add(user2CheckingAccount);
		System.out.println("User 2 Checking Account: " + user2CheckingAccount);
		
		SavingsAccount user2SavingsAccount = new SavingsAccount(user2.getId(), "1024444558898", 140.00);
		savingsAccounts.add(user2SavingsAccount);
		System.out.println("User 2 Savings Account: " + user2SavingsAccount);
		
		System.out.println();
		
		Transaction transaction = new Transaction();
		String accountNumber;
		
		System.out.println("====================== ATM Transaction ======================");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Transfer Balance from Checking to Savings Account");
		System.out.println("4. Transfer Balance from Savings to Checking Account");
		System.out.println("5. Account Balance");
		System.out.println("Press Zero(0) to Exit\n");
		
		System.out.print("Choose your Option: ");
		int flag = sc.nextInt();
		
		while(flag != 0) {
			if(flag == 1) {
				/*Testing Deposit in Checking Account*/
				System.out.println("==================== Deposit Transaction ====================");
				System.out.print("Provide your Checking Account Number: ");
				accountNumber = sc.next();		
				
				System.out.print("Deposit Amount: $");
				Double depositAmount = sc.nextDouble();
				System.out.print("\n");
				
				transaction = new Transaction();
				transaction.deposit(transactions, checkingAccounts, accountNumber, depositAmount);
			}
			
			if(flag == 2) {
				System.out.println("==================== Withdraw Transaction ====================");
				System.out.print("Provide your Checking or Savings Account Number: ");
				accountNumber = sc.next();	
				
				System.out.print("Withdraw Amount: $");
				Double withdrawAmount = sc.nextDouble();
				
				CheckingAccount userCheckingAccount = new CheckingAccount();
				SavingsAccount userSavingAccount = new SavingsAccount();
				
				
				for (CheckingAccount account : checkingAccounts) {
					if(account.getAccountNumber().equals(accountNumber)) {
						userCheckingAccount = account;
					};
				}
				
				String balanceFlag = "";
				
				if(userCheckingAccount.getAccountBalance() != null) {
					while(withdrawAmount > userCheckingAccount.getAccountBalance()) {
						System.out.println("\nYou have no sufficient funds to make this transaction.");
						
						/*System.out.print("Do you want to check your balance?. (Yes or No) : ");						
						balanceFlag = sc.next();
						
						if(balanceFlag.toLowerCase() == "yes" || balanceFlag.toLowerCase() == "y") {
							transaction = new Transaction();
							transaction.balance(savingsAccounts, checkingAccounts, accountNumber);
						}
						else {
							continue;
						}*/
						
						System.out.print("Please choose a different Amount");
						System.out.print("Withdraw Amount: $");
						withdrawAmount = sc.nextDouble();
					}
				}
				else {
					for (SavingsAccount account : savingsAccounts) {
						if(account.getAccountNumber().equals(accountNumber)) {
							userSavingAccount = account;
						};
					}
					
					while(withdrawAmount > userSavingAccount.getAccountBalance()) {
						System.out.println("\nYou have no sufficient funds to make this transaction.");
						
						/*System.out.println("Do you want to check your balance?. Yes or No");						
						balanceFlag = sc.next();
						
						if(balanceFlag.toLowerCase() == "yes" || balanceFlag.toLowerCase() == "y") {
							transaction = new Transaction();
							transaction.balance(savingsAccounts, checkingAccounts, accountNumber);
						}
						else {
							continue;
						}*/
						
						System.out.print("Please choose a different Amount");
						System.out.print("Withdraw Amount: $");
						withdrawAmount = sc.nextDouble();
					}
				}
							
				
				System.out.print("\n");
				
				transaction = new Transaction();
				transaction.withdraw(transactions, checkingAccounts, savingsAccounts, accountNumber, withdrawAmount);
			}
			
			if(flag == 3) {
				System.out.println("==================== Transfer from Checking to Savings Account ====================");
				System.out.print("Provide your Checking Account Number: ");
				String fromAccount = sc.next();	
				
				System.out.print("Provide your Savings Account Number: ");
				String toAccount = sc.next();	
				
				System.out.print("Transfer Amount: $");
				Double amount = sc.nextDouble();
				System.out.print("\n");
				
				transaction = new Transaction();
				transaction.tranferFromCheckingToSaving(transactions, checkingAccounts, savingsAccounts, fromAccount, toAccount, amount);
			}
			
			if(flag == 4) {
				System.out.println("==================== Transfer from Savings to Checking Account ====================");
				
				System.out.print("Provide your Savings Account Number: ");
				String toAccount = sc.next();
				
				System.out.print("Provide your Checking Account Number: ");
				String fromAccount = sc.next();					
				
				System.out.print("Transfer Amount: $");
				Double amount = sc.nextDouble();
				System.out.print("\n");
				
				transaction = new Transaction();
				transaction.tranferFromSavingToChecking(transactions, checkingAccounts, savingsAccounts, fromAccount, toAccount, amount);
			}
			
			if(flag == 5) {
				System.out.print("Provide your Account Number: ");
				accountNumber = sc.next();
				
				transaction = new Transaction();
				BalanceHelper balanceHelper = transaction.balance(savingsAccounts, checkingAccounts, accountNumber);
				System.out.println("======================== Account Balance ========================");
				System.out.println("Account Number: " + balanceHelper.getAccountNumber());
				System.out.println("Account Type: " + balanceHelper.getAccountType());
				System.out.println("Account Balance: $" + balanceHelper.getBalance());
				System.out.println();
			}
			
			System.out.println("Do you want to do another transaction? ");
			System.out.println("If yes, make your selection. Press Zero(0) to Exit\n");
			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. Transfer Balance from Checking to Savings Account");
			System.out.println("4. Transfer Balance from Savings to Checking Account");
			System.out.println("5. Account Balance");
			
			System.out.print("Choose your Option: ");
			flag = sc.nextInt();
		}	
				
		
		System.out.println("*************** Transaction Details ***************");
		System.out.println();
		for(var trans : transactions) {
			System.out.println("---------- Transction " + transactionCount + " ----------");
			System.out.println("Transaction ID: " + trans.getId().toUpperCase());
			System.out.println("Account Number: " + trans.getAccountNumber());
			System.out.println(trans.getTransactionType() + " amount: $" + trans.getAmount());
			System.out.println("Previous account balance: $" + trans.getPreviousBalance());
			System.out.println("Actual account balance: $" + trans.getNewBalance());
			System.out.println("Transaction Date: " + trans.getTransactionDate());
			System.out.println();
			transactionCount++;
		}
		
		System.out.println();
		System.out.println("********** Thanks for doing business with us **********");
		
		System.out.println("\n*************** Account Transactions ***************");
		System.out.print("Provide your Account Number: ");
		accountNumber = sc.next();
		
		transaction = new Transaction();
		List<Transaction> accountTransactions = transaction.accountTransactions(transactions, accountNumber);
		for(var trans : accountTransactions) {
			System.out.println("---------- Transction " + transactionCount + " ----------");
			System.out.println("Transaction ID: " + trans.getId().toUpperCase());
			System.out.println("Account Number: " + trans.getAccountNumber());
			System.out.println(trans.getTransactionType() + " amount: $" + trans.getAmount());
			System.out.println("Previous account balance: $" + trans.getPreviousBalance());
			System.out.println("Actual account balance: $" + trans.getNewBalance());
			System.out.println("Transaction Date: " + trans.getTransactionDate());
			System.out.println();
			transactionCount++;
		}
		
		System.out.println("*************** End Account Transactions ***************");

	}

}
