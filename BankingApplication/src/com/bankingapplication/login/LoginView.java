package com.bankingapplication.login;

import java.util.Scanner;

import com.bankingapplication.admin.AdminView;
import com.bankingapplication.transaction.TransactionView;

public class LoginView implements LoginViewCallBack {

	private Scanner input = new Scanner(System.in);
	private LoginControlerViewCallBack loginControler;

	public LoginView() {
		loginControler = new LoginControler(this);
	}

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.loginIndex();
	}

//=========================Login Index====================================================
	public void loginIndex() { // login index
		System.out.println("\t-----> Welcome to Indian Bank <------\n");
		System.out.println("Enter 1: Admin Login");
		System.out.println("Enter 2: Existing User");
		System.out.println("Enter 3: New User");
		System.out.println("Enter 4: exit");
		char value = input.next().charAt(0);
		System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
		input.nextLine();
		int option = Character.getNumericValue(value);
		switch (option) {
		case 1:
			System.out.println("Welcome to Admin Login --->");
			new AdminView().adminIndex();
			break;
		case 2:
			System.out.println("Welcome to Indian Bank --->");
			System.out.print("Enter Customer Id--->");
			String customerId = input.nextLine();
			System.out.print("Enter password-->");
			String uPassword = input.nextLine();
			loginControler.userVerify(customerId, uPassword);
			break;
		case 3:
			System.out.print("Enter your name --->");
			String name = input.nextLine();
			System.out.print("Enter your Gender --->");
			String gender = input.nextLine();
			System.out.print("Enter your phoneNo -->");
			String phoneNo = input.nextLine();
			System.out.print("Date-Of-Birth (DD/MM/YYYY) --->");
			String dob = input.nextLine();
			System.out.print("Enter your age-->");
			String age = input.nextLine();
			System.out.print("Enter your aadharNo --->");
			String aadharNo = input.nextLine();
			loginControler.addNewUser(name, gender, phoneNo, dob, age, aadharNo);
			break;
		case 4:
			exitMethod();
			break;
		default:
			System.out.println("Enter a valid option");
			System.out.println("--------------------");
			loginIndex();
			break;
		}
	}

//========================================FirstTime Login========================================
	public void firstTimeLogin() {
		long customerId = loginControler.getCustomerId();
		String password = loginControler.getOneTimePassword(customerId);
		System.out.println("This is your customer-->" + customerId);
		System.out.println("This is one time passWord " + password);
		System.out.print("Enter Customer ID --->");
		long firstcustomerId = input.nextLong();
		input.nextLine();
		System.out.print("Enter password --->");
		String firstpassword = input.nextLine();
		loginControler.validateFirstTimeLogin(customerId, password, firstcustomerId, firstpassword);
	}

//======================Printing Messages============================================
	@Override
	public void message(String message) {
		System.out.println();
		System.out.println("\t" + message);
		System.out.println("\t------------");
		continueCheck();
	}

//=====================================Getting new Password===========================
	@Override
	public void newPassword(long customerId) {
		System.out.print("Please Enter new PassWord --->");
		String password = input.nextLine();
		System.out.print("Please re-enter passWord --->");
		String rePassword = input.nextLine();
		loginControler.validatePassword(customerId, password, rePassword);
	}

//======================first timeLogin=======================================
	@Override
	public void firstTimeLoginError(String string) {
		System.out.println(string);
		System.out.println("--------------");
		firstTimeLogin();
	}

//===============================check to continue or Not==========================
	public void continueCheck() {
		System.out.print("Do you want to continue? (y/n) -->");
		String option = input.nextLine();
		loginControler.continueCheck(option);
	}

//==============================Erroe Messages======================================
	@Override
	public void errorMessage(String string) {
		System.out.println(string);
		System.out.println("-------------------");
		continueCheck();
	}

//=============================Exiting method========================================
	@Override
	public void exitMethod() {
		System.out.println("Thank you for visiting...");
	}

//=============================passWord reset message================================
	@Override
	public void passwordResetMessage(String string) {
		System.out.println(string);
		System.out.println("----------------");
		continueCheck();
	}

//============================Account Registration====================================
	@Override
	public void AccoutRegistermessage(String string) {
		System.out.println(string);
		System.out.println("---------------");
		firstTimeLogin();
	}

//===============================loginsuccesfull message==================================
	@Override
	public void loginSuccesfull(long customerId) {
		System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_");
		new TransactionView().transactionIndex(customerId);
	}
}
