package com.bankingapplication.dto;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {

	private long accountNo;
	private long customerId;
	private String name;
	private String gender;
	private long phoneNo;
	private String dob;// convert it to date ;
	private int age;
	private long aadharNo;
	private static String IFSC = "1234IOB000321";
	private Map<Integer, String> ePassword = new HashMap<Integer, String>();
	boolean flag;

	public UserInfo(long accountNo, long customerId, String name, String gender, long phoneNumber, String dob, int age,
			long aadharNumber, boolean flag) {
		this.accountNo = accountNo;
		this.customerId = customerId;
		this.name = name;
		this.gender = gender;
		this.phoneNo = phoneNumber;
		this.dob = dob;
		this.age = age;
		this.aadharNo = aadharNumber;
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public static String getIFSC() {
		return IFSC;
	}

	public Map<Integer, String> getePassword() {
		return ePassword;
	}

	public void setePassword(Map<Integer, String> encryptPassword) {
		this.ePassword = encryptPassword;
	}

}
