package com.bankingapplication.login;

public interface LoginViewCallBack {

	void errorMessage(String string);

	void loginIndex();

	void exitMethod();

	void message(String string);

	void newPassword(long customerId);

	void firstTimeLogin();

	void firstTimeLoginError(String string);

	void passwordResetMessage(String string);

	void AccoutRegistermessage(String string);

	void loginSuccesfull(long id);

}
