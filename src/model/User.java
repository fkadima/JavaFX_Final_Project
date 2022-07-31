package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class User {
	
	private String Id;
	private String FirstName;
	private String LastName;
	private String MiddleInit;
	private String Email;
	private String Address;
	private String City;
	private String State;
	private String Zipcode;
	private String Password;
	
	private CheckingAccount UserCheckingAccount;
	private SavingsAccount UserSavingsAccount;
	private List<Transaction> UserTransactions;
	
	public User() {
		super();
	}


	public User(String firstName, String lastName, String middleInit, String email, String address, String city,
			String state, String zipcode, String password) {
		super();
		Id = generateUserId();
		FirstName = firstName;
		LastName = lastName;
		MiddleInit = middleInit;
		Email = email;
		Address = address;
		City = city;
		State = state;
		Zipcode = zipcode;
		Password = password;
	}
	
	
	public String getId() {
		return Id;
	}
	public void setId() {
		Id = generateUserId();
	}
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getMiddleInit() {
		return MiddleInit;
	}
	public void setMiddleInit(String middleInit) {
		MiddleInit = middleInit;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
	public String getZipcode() {
		return Zipcode;
	}
	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	public CheckingAccount getUserCheckingAccount() {
		return UserCheckingAccount;
	}


	public SavingsAccount getUserSavingsAccount() {
		return UserSavingsAccount;
	}

	public void setUserCheckingAccount(CheckingAccount userCheckingAccount) {
		UserCheckingAccount = userCheckingAccount;
	}
	
	
	public void setUserSavingsAccount(SavingsAccount userSavingsAccount) {
		UserSavingsAccount = userSavingsAccount;
	}
	
	
	public void setUserTransactions(List<Transaction> userTransactions) {
		UserTransactions = userTransactions;
	}

	public List<Transaction> getUserTransactions() {
		return UserTransactions;
	}
	
	/*
	 * @Override public String toString() { return "User [Id=" + Id + ", FirstName="
	 * + FirstName + ", LastName=" + LastName + ", MiddleInit=" + MiddleInit +
	 * ", Email=" + Email + ", Address=" + Address + ", City=" + City + ", State=" +
	 * State + ", Zipcode=" + Zipcode + "]"; }
	 */
	
	



	public void changePin(String currentPin) {
		System.out.println("Pin successfully changed");
	}
	
	@Override
	public String toString() {
		return "User [Id=" + Id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", MiddleInit=" + MiddleInit
				+ ", Email=" + Email + ", Address=" + Address + ", City=" + City + ", State=" + State + ", Zipcode="
				+ Zipcode + ", UserCheckingAccount=" + UserCheckingAccount
				+ ", UserSavingsAccount=" + UserSavingsAccount + ", UserTransactions=" + UserTransactions + "]";
	}


	public void authenticate(String email, String password) {
		System.out.println("User successfully logged in");
	}
	
	
	private String generateUserId() {
		LocalDateTime date = LocalDateTime.now();
		Integer year = date.getYear();
		String sYear = year.toString();
		String last2OfYear = sYear.substring(sYear.length() - 2);
		Integer dayOfMonth = date.getDayOfMonth();
		String day = dayOfMonth.toString();
		String uuid = UUID.randomUUID().toString();
		String last6 = uuid.substring(uuid.length() - 6);
		
		if(day.length() < 2) {
			day = "0" + day;
		}
		
		String userId = (last2OfYear + day + last6).toUpperCase();
		
		return  userId;
	}
	

}
