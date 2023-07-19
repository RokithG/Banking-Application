package com.bankingapplication.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bankingapplication.dto.AccountInfo;
import com.bankingapplication.dto.AdminCredentials;
import com.bankingapplication.dto.AdminInfo;
import com.bankingapplication.dto.LoanRequest;
import com.bankingapplication.dto.TransactionHistory;
import com.bankingapplication.dto.UserCredentials;
import com.bankingapplication.dto.UserInfo;
import com.bankingapplication.encryption.UserEncryption;

public class Repository extends UserEncryption {

	private static Repository repository;

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
			repository.initialValue();
		}
		return repository;
	}

// =========================list to store data=================================	
	private Map<UserCredentials, UserInfo> userDetails = new HashMap();
	private List<AdminCredentials> adminCredential = new ArrayList<>();
	private Map<UserInfo, AccountInfo> accountInfo = new HashMap();
	private List<LoanRequest> loanInfo = new LinkedList();

//===========================initial values=========================================

	private void initialValue() {
		// =======================admin credential=============================
		adminCredential.add(new AdminCredentials("admin", "benjo", 1));
		// ---------------------------USER 1-------------------------//
		UserCredentials user = new UserCredentials(123456, "234567", 1);
		UserInfo userdetail = new UserInfo(123456789l, 123456l, "naveen", "male", 9159474530l, "20/07/2002", 20,
				123456789012l, false);
		userDetails.put(user, userdetail);
		AccountInfo accountInfo = new AccountInfo(123456l, 123456789l, "saving", "2345", 1);
		List<TransactionHistory> history = accountInfo.getTransactionDetail();
		TransactionHistory detail = new TransactionHistory(123456, 20000, "deposit", 123456789l, 123456789l,
				LocalDate.of(2022, 03, 10), LocalTime.of(12, 20));
		history.add(detail);
		detail = new TransactionHistory(123456, 2500, "transfer", 123456789l, 12345671l, LocalDate.of(2022, 03, 10),
				LocalTime.of(12, 20));
		history.add(detail);
		accountInfo.setTransactionDetail(history);
		this.accountInfo.put(userdetail, accountInfo);

		// ------------------------USER 2--------------------------//

		user = new UserCredentials(12345, "23456", 1);
		userdetail = new UserInfo(12345671l, 12345l, "suja", "female", 6385300519l, "23/07/2002", 20, 123456789123l,
				false);
		userDetails.put(user, userdetail);
		accountInfo = new AccountInfo(12345l, 12345671l, "saving", "2345", 1);
		List<TransactionHistory> history1 = accountInfo.getTransactionDetail();
		detail = new TransactionHistory(12345, 26000, "deposit", 12345671l, 12345671l, LocalDate.of(2023, 03, 10),
				LocalTime.of(1, 20));
		history1.add(detail);
		detail = new TransactionHistory(12345, 2600, "transfer", 12345671l, 123456789l, LocalDate.of(2023, 03, 10),
				LocalTime.of(3, 20));
		history1.add(detail);
		accountInfo.setTransactionDetail(history1);
		this.accountInfo.put(userdetail, accountInfo);
	}

//========================check user to login=========================================

	public HashMap checkValidUser(String customerId, String uPassword) { // get encrypted password
		HashMap<Integer, String> credential = new HashMap<>();
		for (UserCredentials check : userDetails.keySet()) {
			String id = String.valueOf(check.getCustomerId());
			if (id.equals(customerId)) {
				credential.put(check.getKey(), check.getPassword());
			}
		}
		return credential;
	}

//==============================adding new User==========================================

	public boolean addNewUser(long accountNo, long customerId, String name, String gender, long phoneNumber, String dob,
			int age, // adding new user
			long aadharNumber, Map<Integer, String> encryptPassword) {
		int keyValue = 0;
		String ePassword = "";
		UserInfo user = new UserInfo(accountNo, customerId, name, gender, phoneNumber, dob, age, aadharNumber, true);
		user.setePassword(encryptPassword);
		UserCredentials credential = new UserCredentials(customerId, ePassword, keyValue);
		userDetails.put(credential, user);
		return true;
	}

//============================getting one time password==================================

	public Map getOneTimePassword(Long customerId) {

		for (UserInfo key : userDetails.values()) {
			if (key.getCustomerId() == customerId) {
				return key.getePassword();
			}
		}
		return null;
	}

//============================adding userCredentials======================================

	public boolean addUserCredentials(long customerId, String password) {
		Map<Integer, String> encrypt = encrypt(password);
		int keyValue = 0;
		String ePassword = "";
		for (Map.Entry val : encrypt.entrySet()) {
			keyValue = (int) val.getKey();
			ePassword = (String) val.getValue();
		}
		for (Map.Entry key : userDetails.entrySet()) {
			UserCredentials info = (UserCredentials) key.getKey();
			if (info.getCustomerId() == customerId) {
				info.setPassword(ePassword);
				info.setKey(keyValue);
			}
		}
		return true;
	}

//========================getting admin credentials to validate==========================

	public AdminCredentials getAdminCredentials(String adminName, String password) {
		for (AdminCredentials credential : adminCredential) {
			if (credential.getUserName().equals(adminName)) {
				return credential;
			}
		}
		return null;
	}

//=============================adding new Admin===================================

	public boolean addNewAdmin(String adminName, String passWord, int key) {
		adminCredential.add(new AdminCredentials(adminName, passWord, key));
		return true;
	}

//================getting flag value to find first time login or not=============

	public boolean getFlag(long customerId) {
		for (Map.Entry key : userDetails.entrySet()) {
			UserInfo credential = (UserInfo) key.getValue();
			if (credential.getCustomerId() == customerId) {
				return credential.isFlag();
			}
		}
		return false;
	}

//=============================adding account Information=========================

	public void addAccountInfo(long customerId, String accountType, int key, String enPin) {
		UserInfo userDetail = null;
		for (Map.Entry val : userDetails.entrySet()) {
			UserInfo value = (UserInfo) val.getValue();
			if (value.getCustomerId() == customerId) {
				userDetail = value;
			}
		}
		userDetail.setFlag(false);
		accountInfo.put(userDetail, new AccountInfo(customerId, userDetail.getAadharNo(), accountType, enPin, key));
	}

//====================to get account information in the form of  hashmap======================

	public Map<UserInfo, AccountInfo> getMyInfo(long customerId) {
		Map<UserInfo, AccountInfo> myInfo = new HashMap();
		for (Map.Entry<UserInfo, AccountInfo> val : accountInfo.entrySet()) {
			UserInfo current = val.getKey();
			if (current.getCustomerId() == customerId) {
				myInfo.put(val.getKey(), val.getValue());
			}
		}
		return myInfo;
	}

//=====================getting account info only===========================
	public AccountInfo getAccountDetail(long customerId) {
		AccountInfo info;
		for (Map.Entry val : accountInfo.entrySet()) {
			info = (AccountInfo) val.getValue();
			if (info.getCustomerId() == customerId) {
				return info;
			}
		}
		return null;
	}

//=======================getting pin to validate transaction======================

	public String getPin(long customerId) {
		String pin = "";
		for (AccountInfo val : accountInfo.values()) {
			if (val.getCustomerId() == customerId) {
				pin = decrypt(val.getPin(), val.getKey());
			}
		}
		return pin;
	}

//========================get receiver account to transfer amount=======================
	public AccountInfo getTransferAccount(long transferAccount) {
		for (AccountInfo val : accountInfo.values()) {
			if (val.getAccountNumber() == transferAccount) {
				return val;
			}
		}
		return null;
	}

//================================getting transactionHistory==========================

	public List<TransactionHistory> getTransactionHistory(long customerId) {
		AccountInfo info = getAccountDetail(customerId);
		return info.getTransactionDetail();
	}
//====================================loan Request=======================================

	public void loanRequest(double loanAmount, double salary, long customerId) {
		loanInfo.add(new LoanRequest(loanAmount, salary, customerId, true, false));
	}

	public int getLoanRequest() {
		int count = 0;
		for (LoanRequest val : loanInfo) {
			if (val.isLoanRequest()) {
				count++;
			}
		}
		return count;
	}

	public List<LoanRequest> getLoanApproval() {
		return loanInfo;
	}

	public void validateLoan(double id, boolean check) {
		for (LoanRequest val : loanInfo) {
			if (val.getCustomerId() == id) {
				val.setLoanRequest(false);
				if (check == true) {
					val.setApproval(check);
				} else {
					val.setApproval(check);
				}
			}
		}
	}

	public boolean getAsked(long customerId) {

		for (LoanRequest val : loanInfo) {
			if (val.getCustomerId() == customerId) {
				return val.isAsked();
			}
		}
		return false;
	}

	public boolean getApproval(long customerId) {
		for (LoanRequest val : loanInfo) {
			if (val.getCustomerId() == customerId) {
				return val.isApproval();
			}
		}
		return false;
	}

	public boolean getRequest(long customerId) {
		for (LoanRequest val : loanInfo) {
			if (val.getCustomerId() == customerId) {
				return val.isLoanRequest();
			}
		}
		return false;
	}

	public void setAsked(boolean b, long customerId) {
		for (LoanRequest val : loanInfo) {
			if (val.getCustomerId() == customerId) {
				val.setAsked(b);
			}
		}
	}
}
