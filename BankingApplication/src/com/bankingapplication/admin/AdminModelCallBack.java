package com.bankingapplication.admin;

import java.util.List;

import com.bankingapplication.dto.LoanRequest;

public interface AdminModelCallBack {

	boolean checkCredentials(String adminName, String password);

	boolean addNewUser(String adminName, String aPassword);

	int getLoanRequest();

	List<LoanRequest> getAccountLoan();

	void validateAccount(double id, boolean check);

}
