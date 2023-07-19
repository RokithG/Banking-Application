package com.bankingapplication.transaction;

public interface TransactionControlerViewCallBack {

	boolean getFlag(long customerId);

	void addAccountInfo(long customerId, String accountType, String strPin, String strRePin);

	void setCustomerId(long customerId);

	long getCustomerId();

	void getMyInfo();

	void continueCheck(String option);

	void withDraw(double amount);

	void deposite(double depositeAmount);

	double Balance();

	void checkBalance(double transferAmount);

	void getTransactionHistory();

	void loanRequest(double loanAmount, double salary);

	boolean getAsked(long customerId);

	boolean getApproval(long customerId);

	boolean getRequest(long customerId);

	void setAsked(boolean b, long customerId);




}
