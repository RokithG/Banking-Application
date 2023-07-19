package com.bankingapplication.login;

public class LoginControler implements LoginControlerViewCallBack, LoginControlerModelCallBack {

	private LoginViewCallBack loginView;
	private LoginModelCallBack loginModel;

	public LoginControler(LoginView loginView) {
		this.loginView = loginView;
		loginModel = new LoginModel(this);
	}

//===================================Verify User===========================================
	@Override
	public void userVerify(String customerId, String uPassword) {

		if (customerId.matches("[0-9]+")) {
			if (loginModel.userVerify(customerId, uPassword)) {
				long Id = Long.parseLong(customerId);
				loginView.loginSuccesfull(Id);
			} else {
				loginView.errorMessage("your passWord is Wrong");
			}
		} else {
			loginView.errorMessage("Invalid Customer ID");
		}
	}

//==========================Adding New User==========================================
	@Override
	public void addNewUser(String name, String gender, String phoneNo, String dob, String age, String aadharNo) {// getting
																													// user
																													// info
																													// to
																													// store
		name = name.toLowerCase();
		gender = gender.toLowerCase();
		if (!name.matches("[a-z]+")) {
			loginView.errorMessage("Enter a valid Name");
		} else if (!gender.equals("male") && !(gender.equals("female"))) {
			loginView.errorMessage("Enter a valid input");
		} else if (!phoneNo.matches("[0-9]+") && !(phoneNo.length() == 10)) {
			loginView.errorMessage("Enter a valid PhoneNo");
		} else if (!age.matches("[0-9]+")) {
			loginView.errorMessage("Enter a Valid age");
		} else if (!dob.matches("[0-9/]+")) {
			loginView.errorMessage("Please enter a valid Dob");
		} else if (!aadharNo.matches("[0-9]+") && !(aadharNo.length() == 12)) {
			loginView.errorMessage("Please enter a valid aadhar No");
		} else {
			long phoneNumber = Long.parseLong(phoneNo);
			long aadharNumber = Long.parseLong(aadharNo);
			int ageNumber = Integer.parseInt(age);

			if (loginModel.addNewUser(name, gender, phoneNumber, dob, ageNumber, aadharNumber)) {
				loginView.AccoutRegistermessage("Succesfully added");
			} else {
				loginView.message("Uable to Add new User");
			}
		}
	}

//=============================Check do you Want to continue============================
	@Override
	public void continueCheck(String option) {
		if (option.equals("y") || option.equals("Y") || option.equals("yes") || option.equals("YES")) {
			loginView.loginIndex();
		} else {
			loginView.exitMethod();
		}
	}

//==================================getting customerId===================================
	@Override
	public long getCustomerId() {
		return loginModel.getCustomerId();
	}

//================================getting one time password to show for customer==================
	@Override
	public String getOneTimePassword(long customerId) {
		return loginModel.getOneTimePassword(customerId);// work
	}

//================================validate first time login==============================
	@Override
	public void validateFirstTimeLogin(long customerId, String password, long firstcustomerId, String firstpassword) {
		if (customerId == firstcustomerId) {
			if (password.equals(firstpassword)) {
				loginView.newPassword(customerId);
			}
		} else {
			loginView.firstTimeLoginError("Please check your customerId");
		}
	}

//==============================validate normal signup========================
	@Override
	public void validatePassword(long customerId, String password, String rePassword) {
		if (password.equals(rePassword)) {
			if (loginModel.addPassWord(customerId, password)) {
				loginView.passwordResetMessage("User name and password added succesfully");
			}
		} else {
			loginView.firstTimeLogin();
		}

	}
}
