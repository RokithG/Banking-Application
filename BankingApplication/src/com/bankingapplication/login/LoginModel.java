package com.bankingapplication.login;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.bankingapplication.Repository.Repository;
import com.bankingapplication.encryption.UserEncryption;

public class LoginModel extends UserEncryption implements LoginModelCallBack {

	private LoginControlerViewCallBack loginControler;

	public LoginModel(LoginControler loginControler) {
		this.loginControler = loginControler;
	}

	private long customerId;

//======================================verify user credentials=========================================
	public boolean userVerify(String customerId, String uPassword) {

		HashMap<Integer, String> credential = Repository.getInstance().checkValidUser(customerId, uPassword);
		if (credential.size() == 0) {
			return false;
		} else {
			int key = 0;
			String encrypt = "";
			for (Map.Entry m : credential.entrySet()) {
				key = (int) m.getKey();
				encrypt = (String) m.getValue();
			}
			String password = decrypt(encrypt, key);
			if (password.equals(uPassword)) {

				this.customerId = Long.parseLong(customerId);
				return true;
			}
		}
		return false;
	}

//==================================adding new user==================================
	@Override
	public boolean addNewUser(String name, String gender, long phoneNumber, String dob, int ageNumber, // adding new
																										// User
			long aadharNumber) {
		long customerID = System.currentTimeMillis() / 6000;
		this.customerId = customerID;
		String oneTimePassword = generateOneTimePassWord();
		Map<Integer, String> encryptPassword = encrypt(oneTimePassword);
		long accountNo = System.currentTimeMillis();
		return Repository.getInstance().addNewUser(accountNo, customerID, name, gender, phoneNumber, dob, ageNumber,
				aadharNumber, encryptPassword);
	}

//=================================method to generate one time password====================
	private String generateOneTimePassWord() {
		UUID uuid = UUID.randomUUID();
		String value = uuid.toString().replace("-", "");
		String password = value.substring(value.length() - 8);
		return password;
	}

//=======================method to get customer id================================
	@Override
	public long getCustomerId() {
		return customerId;
	}

//=======================method to get one time password==========================
	@Override
	public String getOneTimePassword(long customerId) {
		Map<Integer, String> password = Repository.getInstance().getOneTimePassword(customerId);
		int key = 0;
		String ePassword = "";
		for (Map.Entry value : password.entrySet()) {
			key = (int) value.getKey();
			ePassword = (String) value.getValue();
		}
		String dPassword = decrypt(ePassword, key);
		return dPassword;
	}

//==================adding new password==============================
	@Override
	public boolean addPassWord(long customerId, String password) {
		return Repository.getInstance().addUserCredentials(customerId, password);
	}

}
