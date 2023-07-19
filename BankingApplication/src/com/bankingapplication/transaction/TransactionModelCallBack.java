package com.bankingapplication.transaction;

import java.util.List;

import com.bankingapplication.dto.TransactionHistory;

public interface TransactionModelCallBack {

	boolean getFlag(long customerId);

	void setCustomerId(long customerId);

	long getCustomerId();

	void addAccountInfo(long customerId, String accountType, String strPin);

	void getMyInfo();

	boolean withdraw(double amount);

	boolean checkPin(String pin);

	boolean deposite(double depositeAmount);

	double showBalance();

	boolean checkBalance(double transferAmount);

	boolean transferAmount(long transferAccount, double transferAmount);

	List<TransactionHistory> getTransactionHistory();

	void loanRequest(double loanAmount, double salary);

	boolean getAsked(long customerId);

	boolean getApproval(long customerId);

	boolean getRequest(long customerId);

	void setAsked(boolean b, long customerId);
}
