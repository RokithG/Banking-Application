package com.bankingapplication.transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.bankingapplication.Repository.Repository;
import com.bankingapplication.dto.AccountInfo;
import com.bankingapplication.dto.TransactionHistory;
import com.bankingapplication.dto.UserInfo;
import com.bankingapplication.encryption.UserEncryption;

public class TransactionModel extends UserEncryption implements TransactionModelCallBack {

	private TransactionControlerModelCallBack transactionControler;
	private long customerId;

	public TransactionModel(TransactionControler transactionControler) {
		this.transactionControler = transactionControler;
	}

	@Override
	public boolean getFlag(long customerId) {
		return Repository.getInstance().getFlag(customerId);
	}

	@Override
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public long getCustomerId() {
		return customerId;
	}

	// -------------------ADDING ACCOUNT INFO-----------------------
	@Override
	public void addAccountInfo(long customerId, String accountType, String strPin) {
		Map<Integer, String> encrypt = encrypt(strPin);
		int key = 0;
		String enPin = "";
		for (Map.Entry val : encrypt.entrySet()) {
			key = (int) val.getKey();
			enPin = (String) val.getValue();
		}
		Repository.getInstance().addAccountInfo(customerId, accountType, key, enPin);
	}

	// -----------------------------------Getting User
	// Info------------------------------------------------
	@Override
	public void getMyInfo() {
		Map<UserInfo, AccountInfo> myInfo = Repository.getInstance().getMyInfo(customerId);
		transactionControler.showMyInfo(myInfo);
	}

	// -------------------------------WithDraw
	// Amount-----------------------------------
	@Override
	public boolean withdraw(double amount) {
		AccountInfo info = Repository.getInstance().getAccountDetail(customerId);
		addTransactionHistory(info, amount, "debit", info.getAccountNumber(), info.getAccountNumber());
		if (info.getBalance() > amount) {
			String pin = transactionControler.getPin();
			if (checkPin(pin)) {
				info.setBalance(info.getBalance() - amount);
				return true;
			}
		}
		return false;
	}

	// ------------------------------ADD TRANSACTION
	// HISTORY-------------------------
	private void addTransactionHistory(AccountInfo info, double amount, String option, long sender, long receiver) {
		List<TransactionHistory> history = info.getTransactionDetail();
		history.add(
				new TransactionHistory(customerId, amount, option, sender, receiver, LocalDate.now(), LocalTime.now()));
		info.setTransactionDetail(history);
	}

	@Override
	public boolean checkPin(String pin) {
		String userPin = Repository.getInstance().getPin(customerId);
		if (pin.equals(userPin)) {
			return true;
		}
		return false;
	}

	// ------------------------------Deposite Amount--------------------------
	@Override
	public boolean deposite(double depositeAmount) {
		AccountInfo info = Repository.getInstance().getAccountDetail(customerId);
		String pin = transactionControler.getPin();
		if (checkPin(pin)) {
			info.setBalance(info.getBalance() + depositeAmount);
			addTransactionHistory(info, depositeAmount, "credit", info.getAccountNumber(), info.getAccountNumber());
			return true;
		}
		return false;
	}

	@Override
	public double showBalance() {
		AccountInfo info = Repository.getInstance().getAccountDetail(customerId);
		return info.getBalance();
	}

	@Override
	public boolean checkBalance(double transferAmount) {
		AccountInfo info = Repository.getInstance().getAccountDetail(customerId);
		if (info.getBalance() >= transferAmount) {
			return true;
		}
		return false;
	}

	// --------------------------Transfer
	// Amount----------------------------------------
	@Override
	public boolean transferAmount(long transferAccount, double transferAmount) {

		AccountInfo senderAccount = Repository.getInstance().getAccountDetail(customerId);
		AccountInfo receiverAccount = Repository.getInstance().getTransferAccount(transferAccount);
		if (receiverAccount == null) {
			transactionControler.errorMessage("Please Enter a Valid Account No.");
		}
		addTransactionHistory(senderAccount, transferAmount, "debit", senderAccount.getAccountNumber(),
				receiverAccount.getAccountNumber());
		addTransactionHistory(receiverAccount, transferAmount, "credit", senderAccount.getAccountNumber(),
				receiverAccount.getAccountNumber());
		receiverAccount.setBalance(receiverAccount.getBalance() + transferAmount);
		senderAccount.setBalance(senderAccount.getBalance() - transferAmount);
		return true;
	}

	@Override
	public List<TransactionHistory> getTransactionHistory() {
		return Repository.getInstance().getTransactionHistory(customerId);
	}

//=======================================for loan request==========================================================
	@Override
	public void loanRequest(double loanAmount, double salary) {
		Repository.getInstance().loanRequest(loanAmount, salary, customerId);
	}

	@Override
	public boolean getAsked(long customerId) {
		return Repository.getInstance().getAsked(customerId);
	}

	@Override
	public boolean getApproval(long customerId) {
		return Repository.getInstance().getApproval(customerId);
	}

	@Override
	public boolean getRequest(long customerId) {
		return Repository.getInstance().getRequest(customerId);
	}

	@Override
	public void setAsked(boolean b, long customerId) {
		Repository.getInstance().setAsked(b, customerId);

	}

}
