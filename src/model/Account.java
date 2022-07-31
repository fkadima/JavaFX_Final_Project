package model;

import java.time.LocalDateTime;

public class Account {
	private String UserId;
	private String AccountNumber;
	private String CardNumber;
	private String CardPin;
	private Double AccountBalance;
	private LocalDateTime CreatedAt;
	private LocalDateTime UpdateAt;
	private LocalDateTime AccessedOn;
	
	public Account() {
		super();
	}

	public Account(String userId, String accountNumber, Double accountBalance) {
		super();	
		
		UserId = userId;
		AccountNumber = accountNumber;
		AccountBalance = accountBalance;
		CreatedAt = LocalDateTime.now();
		UpdateAt = LocalDateTime.now();
		AccessedOn = LocalDateTime.now();
	}

	public Account(String userId, String accountNumber, String cardNumber, String cardPin, Double accountBalance) {
		super();
		UserId = userId;
		AccountNumber = accountNumber;
		CardNumber = cardNumber;
		CardPin = cardPin;
		AccountBalance = accountBalance;
		CreatedAt = LocalDateTime.now();
		UpdateAt = LocalDateTime.now();
		AccessedOn = LocalDateTime.now();
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}

	public String getCardPin() {
		return CardPin;
	}

	public void setCardPin(String cardPin) {
		CardPin = cardPin;
	}

	public Double getAccountBalance() {
		return AccountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		AccountBalance = accountBalance;
	}

	public LocalDateTime getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		CreatedAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return UpdateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		UpdateAt = updateAt;
	}

	public LocalDateTime getAccessedOn() {
		return AccessedOn;
	}

	public void setAccessedOn(LocalDateTime accessedOn) {
		AccessedOn = accessedOn;
	}
	
	
}
