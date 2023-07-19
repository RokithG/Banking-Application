package com.bankingapplication.dto;

public class AdminCredentials extends AdminInfo {

	private String passWord;
	private int key;

	public AdminCredentials(String userName, String passWord, int key) {
		super(userName);
		this.passWord = passWord;
		this.key = key;
	}

	public String getPassWord() {
		return passWord;
	}

	public int getKey() {
		return key;
	}

}
