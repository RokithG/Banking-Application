package com.bankingapplication.transaction;

import java.util.Map;

import com.bankingapplication.dto.AccountInfo;
import com.bankingapplication.dto.UserInfo;

public interface TransactionControlerModelCallBack {

	void showMyInfo(Map<UserInfo, AccountInfo> myInfo);

	String getPin();

	void errorMessage(String string);

}
