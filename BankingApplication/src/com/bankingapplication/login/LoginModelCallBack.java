package com.bankingapplication.login;

public interface LoginModelCallBack {

	boolean userVerify(String customerId, String uPassword);

	boolean addNewUser(String name, String gender, long phoneNumber, String dob, int ageNumber, long aadharNumber);

	long getCustomerId();

	String getOneTimePassword(long customerId);

	boolean addPassWord(long customerId, String password);

}
