package com.bankingapplication.login;

public interface LoginControlerViewCallBack {

	void userVerify(String customerId, String uPassword);

	void continueCheck(String option);

	void addNewUser(String name, String gender, String phoneNo, String dob, String age, String aadharNo);

	long getCustomerId();

	String getOneTimePassword(long customerId);

	void validateFirstTimeLogin(long customerId, String password, long firstcustomerId, String firstpassword);

	void validatePassword(long customerId, String password, String rePassword);
	

}
