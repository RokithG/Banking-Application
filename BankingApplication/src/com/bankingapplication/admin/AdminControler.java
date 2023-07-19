package com.bankingapplication.admin;

import java.util.List;

import com.bankingapplication.dto.LoanRequest;

public class AdminControler implements AdminControlerViewCallBack, AdminControlerModelCallBack {

	private AdminViewCallBack adminView;
	private AdminModelCallBack adminModel;

	public AdminControler(AdminView adminView) {
		this.adminView = adminView;
		this.adminModel = new AdminModel(this);
	}

	@Override
	public void checkCredentials(String adminName, String password) {
		if (adminModel.checkCredentials(adminName, password)) {
			adminView.loginSuccesfull("Login Succesfull");
		} else {
			adminView.loginFailure("Enter a valid credential");
		}
	}

	@Override
	public void continueCheck(String option) {
		if (option.equals("y") || option.equals("Y") || option.equals("yes") || option.equals("YES")) {
			adminView.adminMenu();
		} else {
			adminView.exitMethod();
		}
	}

	@Override
	public void addNewAdmin(String adminName, String aPassword) {
		if (adminModel.addNewUser(adminName, aPassword)) {
			adminView.loginSuccesfull("New Admin added Succesfully");
		} else {
			adminView.loginFailure("Unable to Add new Admin");
		}
	}

	@Override
	public void errorMessage(String string) {
		adminView.errorMessage(string);

	}

//========================loan request====================
	@Override
	public int loanRequest() {
		return adminModel.getLoanRequest();
	}

	@Override
	public List<LoanRequest> getLoanRequest() {
		List<LoanRequest> info = adminModel.getAccountLoan();
		return info;
	}

	@Override
	public void validateAdminCheck(double id, boolean check) {
		adminModel.validateAccount(id, check);

	}

}
