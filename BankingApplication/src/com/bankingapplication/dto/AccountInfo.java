package com.bankingapplication.dto;

import java.util.LinkedList;
import java.util.List;

public class AccountInfo {
	private long customerId;
	private long accountNumber;
	private static String IFSC = "1234IOB000321";
	private String accountType;
	private String pin;
	private int key;
	private double balance = 2000;
	private List<TransactionHistory> transactionDetail = new LinkedList();

	public AccountInfo(long customerId, long accountNumber, String accountType, String pin, int key) {
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.pin = pin;
		this.key = key;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static String getIFSC() {
		return IFSC;
	}

	public static void setIFSC(String iFSC) {
		IFSC = iFSC;
	}

	public List<TransactionHistory> getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(List<TransactionHistory> transactionDetail) {
		this.transactionDetail = transactionDetail;
	}
}
