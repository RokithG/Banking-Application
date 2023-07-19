package com.bankingapplication.admin;

import java.util.List;
import java.util.Map;

import com.bankingapplication.Repository.Repository;
import com.bankingapplication.dto.AdminCredentials;
import com.bankingapplication.dto.LoanRequest;
import com.bankingapplication.encryption.UserEncryption;

public class AdminModel extends UserEncryption implements AdminModelCallBack {

	private AdminControlerModelCallBack adminControler;

	public AdminModel(AdminControler adminControler) {
		this.adminControler = adminControler;
	}

	@Override
	public boolean checkCredentials(String adminName, String password) {
		AdminCredentials credential = Repository.getInstance().getAdminCredentials(adminName, password);
		if (credential == null) {
			adminControler.errorMessage("Enter a Valid userName");
		}
		String checkPassword = decrypt(credential.getPassWord(), credential.getKey());
		if (password.equals(checkPassword)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addNewUser(String adminName, String aPassword) {
		Map<Integer, String> password = encrypt(aPassword);
		int key = 0;
		String passWord = "";
		for (Map.Entry set : password.entrySet()) {
			key = (int) set.getKey();
			passWord = (String) set.getValue();
		}
		return Repository.getInstance().addNewAdmin(adminName, passWord, key);
	}

//=========================================loan Request======================================
	@Override
	public int getLoanRequest() {
		return Repository.getInstance().getLoanRequest();
	}

	@Override
	public List<LoanRequest> getAccountLoan() {
		return Repository.getInstance().getLoanApproval();
	}

	@Override
	public void validateAccount(double id, boolean check) {
		Repository.getInstance().validateLoan(id, check);

	}

}
