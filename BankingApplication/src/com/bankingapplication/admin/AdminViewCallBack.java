package com.bankingapplication.admin;

public interface AdminViewCallBack {

	void loginSuccesfull(String string);

	void loginFailure(String string);

	void adminIndex();

	void exitMethod();

	void errorMessage(String string);

	void adminMenu();

}
