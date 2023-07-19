package com.bankingapplication.dto;

public class UserCredentials {

	private String password;
	private int key;
	private long customerId;
	boolean flag = false;

	public UserCredentials(long customerId, String password, int key) {
		this.customerId = customerId;
		this.password = password;
		this.key = key;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public int getKey() {
		return key;
	}

	public long getCustomerId() {
		return customerId;
	}

}
