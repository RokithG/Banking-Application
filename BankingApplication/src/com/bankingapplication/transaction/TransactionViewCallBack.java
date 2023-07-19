package com.bankingapplication.transaction;

import java.util.List;
import java.util.Map;

import com.bankingapplication.dto.AccountInfo;
import com.bankingapplication.dto.TransactionHistory;
import com.bankingapplication.dto.UserInfo;

public interface TransactionViewCallBack {


	void pinErrorMessage(String string);

	void showMyInfo(Map<UserInfo, AccountInfo> myInfo);

	void transactionIndex(long l);

	void exitMethod();

	void successMessage(String string);

	void failureMessage(String string);

	String getPin();

	long getTransferAccount();

	void errorMessage(String string);

	void showHistory(List<TransactionHistory> history);

}
