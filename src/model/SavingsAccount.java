package model;

public class SavingsAccount extends Account {
	
	private Integer WithdrawLimit;

	public SavingsAccount() {
		super();
	}	
	

	public SavingsAccount(String userId, String accountNumber, Double accountBalance) {
		super(userId, accountNumber, accountBalance);
		// TODO Auto-generated constructor stub
	}


	public SavingsAccount(String userId, String accountNumber, String cardNumber, String cardPin,
			Double accountBalance) {
		super(userId, accountNumber, cardNumber, cardPin, accountBalance);
		// TODO Auto-generated constructor stub
	}



	public SavingsAccount(String userId, String accountNumber, String cardNumber, String cardPin, 
			Double accountBalance, Integer withdrawLimit) {
		super(userId, accountNumber, cardNumber, cardPin, accountBalance);
		WithdrawLimit = withdrawLimit;
	}

	public Integer getWithdrawLimit() {
		return WithdrawLimit;
	}

	public void setWithdrawLimit(Integer withdrawLimit) {
		WithdrawLimit = withdrawLimit;
	}

	@Override
	public String toString() {
		return "SavingsAccount [getUserId()=" + getUserId()
				+ ", getAccountNumber()=" + getAccountNumber() + ", getCardNumber()=" + getCardNumber()
				+ ", getCardPin()=" + getCardPin() + ", getAccountBalance()=" + getAccountBalance()
				+ ", getCreatedAt()=" + getCreatedAt() + ", getUpdateAt()=" + getUpdateAt() + ", getAccessedOn()="
				+ getAccessedOn() + "]";
	}

}
