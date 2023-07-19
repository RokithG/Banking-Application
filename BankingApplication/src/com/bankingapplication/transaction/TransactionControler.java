package com.bankingapplication.transaction;

import java.util.List;
import java.util.Map;

import com.bankingapplication.dto.AccountInfo;
import com.bankingapplication.dto.TransactionHistory;
import com.bankingapplication.dto.UserInfo;

public class TransactionControler implements TransactionControlerModelCallBack, TransactionControlerViewCallBack {

	private TransactionViewCallBack transactionView;
	private TransactionModelCallBack transactionModel;

	public TransactionControler(TransactionView transactionView) {
		this.transactionView = transactionView;
		transactionModel = new TransactionModel(this);
	}

	@Override
	public boolean getFlag(long customerId) {
		return transactionModel.getFlag(customerId);
	}

	public void addAccountInfo(long customerId, String accountType, String strPin, String strRePin) {
		if (strPin.equals(strRePin)) {
			transactionModel.addAccountInfo(customerId, accountType, strPin);
		} else {
			transactionView.pinErrorMessage("Password Mis-Match");
		}
	}

	@Override
	public void setCustomerId(long customerId) {
		transactionModel.setCustomerId(customerId);
	}

	@Override
	public long getCustomerId() {
		return transactionModel.getCustomerId();
	}

	public void getMyInfo() {
		transactionModel.getMyInfo();
	}

	@Override
	public void showMyInfo(Map<UserInfo, AccountInfo> myInfo) {
		transactionView.showMyInfo(myInfo);
	}

	@Override
	public void continueCheck(String option) {
		if (option.equals("y") || option.equals("Y") || option.equals("yes") || option.equals("YES")) {
			transactionView.transactionIndex(transactionModel.getCustomerId());
		} else {
			transactionView.exitMethod();
		}

	}

	@Override
	public void withDraw(double amount) {
		if (transactionModel.withdraw(amount)) {
			transactionView.successMessage("Withdraw Succesfully");
		} else {
			transactionView.failureMessage("your Balance is low");
		}
	}

	@Override
	public String getPin() {
		return transactionView.getPin();
	}

	@Override
	public void deposite(double depositeAmount) {
		if (transactionModel.deposite(depositeAmount)) {
			transactionView.successMessage("Deposited Succesfully");
		} else {
			transactionView.failureMessage("Unable to Deposit");
		}

	}

	@Override
	public double Balance() {
		return transactionModel.showBalance();
	}

	@Override
	public void checkBalance(double transferAmount) {// for bank transfer
		if (transactionModel.checkBalance(transferAmount)) {
			long transferAccount = transactionView.getTransferAccount();
			if (transactionModel.transferAmount(transferAccount, transferAmount)) {
				transactionView.successMessage("Transfered Succesfully");
			} else {
				transactionView.failureMessage("Unable to transfer");
			}
		} else {
			transactionView.failureMessage("Low Balance");
		}
	}

	@Override
	public void errorMessage(String string) {
		transactionView.errorMessage(string);
	}

	@Override
	public void getTransactionHistory() {
		List<TransactionHistory> history = transactionModel.getTransactionHistory();
		if (history.size() == 0) {
			transactionView.errorMessage("No available Transaction Details");
		} else {
			transactionView.showHistory(history);
		}

	}

//---------------------------------for requesting loans-------------------------------------------------------
	@Override
	public void loanRequest(double loanAmount, double salary) {
		transactionModel.loanRequest(loanAmount, salary);
	}

	@Override
	public boolean getAsked(long customerId) {
		return transactionModel.getAsked(customerId);
	}

	@Override
	public boolean getApproval(long customerId) {
		return transactionModel.getApproval(customerId);
	}

	@Override
	public boolean getRequest(long customerId) {
		return transactionModel.getRequest(customerId);
	}

	@Override
	public void setAsked(boolean b, long customerId) {
		transactionModel.setAsked(b, customerId);

	}

}
