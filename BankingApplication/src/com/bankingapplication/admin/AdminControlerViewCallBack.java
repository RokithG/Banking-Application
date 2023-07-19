package com.bankingapplication.admin;

import java.util.List;

import com.bankingapplication.dto.LoanRequest;

public interface AdminControlerViewCallBack {

	void checkCredentials(String adminName, String password);

	void continueCheck(String option);

	void addNewAdmin(String adminName, String aPassword);

	int loanRequest();

	List<LoanRequest> getLoanRequest();

	void validateAdminCheck(double id, boolean check);

}
