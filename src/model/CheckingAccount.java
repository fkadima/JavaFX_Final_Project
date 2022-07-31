package model;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public CheckingAccount(String userId, String accountNumber, Double accountBalance) {
		super(userId, accountNumber, accountBalance);
		// TODO Auto-generated constructor stub
	}


	public CheckingAccount(String userId, String accountNumber, String cardNumber, String cardPin, Double accountBalance) {
		super(userId, accountNumber, cardNumber, cardPin, accountBalance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CheckingAccount [getUserId()=" + getUserId() + ", getAccountNumber()=" + getAccountNumber()
				+ ", getCardNumber()=" + getCardNumber() + ", getCardPin()=" + getCardPin() + ", getAccountBalance()="
				+ getAccountBalance() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdateAt()=" + getUpdateAt()
				+ ", getAccessedOn()=" + getAccessedOn() + "]";
	}

}
