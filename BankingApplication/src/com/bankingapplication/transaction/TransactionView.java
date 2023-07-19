package com.bankingapplication.transaction;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bankingapplication.dto.AccountInfo;
import com.bankingapplication.dto.TransactionHistory;
import com.bankingapplication.dto.UserInfo;
import com.bankingapplication.login.LoginView;

public class TransactionView implements TransactionViewCallBack {

	private Scanner input = new Scanner(System.in);
	private TransactionControlerViewCallBack transactionControler;

	public TransactionView() {
		transactionControler = new TransactionControler(this);
	}

	public void transactionIndex(long customerId) {
		transactionControler.setCustomerId(customerId);
		boolean flag = transactionControler.getFlag(customerId);
		if (flag) {
			System.out.println("Please provide the further Details to complete registration");
			accountInfo();
		}
		boolean asked = transactionControler.getAsked(customerId);
		boolean approval = transactionControler.getApproval(customerId);
		boolean request = transactionControler.getRequest(customerId);
		if (asked == true) {
			if (request == true)
				System.out.println("\t\tYour Loan Request is Underr processs");
			else if (approval == false && request == false) {
				System.out.println("\t Unable to give loan to your account");
				transactionControler.setAsked(false, customerId);
			} else if (approval == true && request == false) {
				System.out.println("\t Your Loan request is accepted");
				transactionControler.setAsked(false, customerId);
			}

		}
		System.out.println("Enter 1: My Profile");
		System.out.println("Enter 2: WithDraw");
		System.out.println("Enter 3: Deposit");
		System.out.println("Enter 4: Bank Transfer");
		System.out.println("Enter 5: Balance Enquiry");
		System.out.println("Enter 6: Transaction History");
		System.out.println("Enter 7: Apply for Loan");
		System.out.println("Enter 8: LogOut");
		System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");
		char value = input.next().charAt(0);
		input.nextLine();
		int option = Character.getNumericValue(value);
		switch (option) {
		case 1:
			transactionControler.getMyInfo();
			break;
		case 2:
			System.out.print("Enter amount to Widhdraw-->");
			double amount = input.nextDouble();
			input.nextLine();
			transactionControler.withDraw(amount);
			break;
		case 3:
			System.out.print("Enter amount to Deposit --->");
			double depositeAmount = input.nextDouble();
			input.nextLine();
			transactionControler.deposite(depositeAmount);
			break;
		case 4:
			System.out.print("Enter amount to transfer --->");
			double transferAmount = input.nextDouble();
			input.nextLine();
			transactionControler.checkBalance(transferAmount);
		case 5:
			System.out.println("Your Current balance is -->" + transactionControler.Balance());
			System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
			checkContinue();
			break;
		case 6:
			transactionControler.getTransactionHistory();
			break;
		case 7:
			loanRequest();
		case 8:
			new LoginView().loginIndex();
			break;
		default:
			System.out.println("Enter a valid option");
			System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
			transactionIndex(customerId);
		}
	}

	private void accountInfo() {
		System.out.print("Please Select Your Account Type (Saving/Current) (S/C)--->");
		char c = input.next().charAt(0);
		String accountType = "";
		if (c == 's' || c == 'S')
			accountType = "saving";
		else if (c == 'c' || c == 'C')
			accountType = "current";
		else {
			System.out.println("Enter a valid option..");
			accountInfo();
		}
		input.nextLine();
		System.out.println("Enter new Transaction Pin");
		String strPin = input.nextLine();
		System.out.println("Re-enter pin");
		String strRePin = input.nextLine();
		long customerId = transactionControler.getCustomerId();
		transactionControler.addAccountInfo(customerId, accountType, strPin, strRePin);
	}

	@Override
	public void pinErrorMessage(String string) {
		System.out.println(string);
		System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_");
		accountInfo();
	}

	@Override
	public void showMyInfo(Map<UserInfo, AccountInfo> myInfo) {
		UserInfo userInfo = null;
		AccountInfo accountInfo = null;
		for (Map.Entry val : myInfo.entrySet()) {
			accountInfo = (AccountInfo) val.getValue();
			userInfo = (UserInfo) val.getKey();
		}
		System.out.println("Name -->" + userInfo.getName());
		System.out.println("customerId --->" + userInfo.getCustomerId());
		System.out.println("Account Number --> " + userInfo.getAccountNo());
		System.out.println("IFSC code --->" + userInfo.getIFSC());
		System.out.println("Date-Of-Birth --->" + userInfo.getDob());
		System.out.println("Phone Number-->" + userInfo.getPhoneNo());
		System.out.println("Aadhar No--->" + userInfo.getAadharNo());
		System.out.println("Account Balance --> " + accountInfo.getBalance());
		System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
		checkContinue();
	}

	@Override
	public String getPin() {
		System.out.print("Enter your Pin -->");
		String pin = input.nextLine();
		return pin;
	}

	private void checkContinue() {
		System.out.print("Do you want to continue? (y/n) -->");
		String option = input.nextLine();
		transactionControler.continueCheck(option);

	}

	@Override
	public void exitMethod() {
		System.out.println("Thanks For visiting us.");
	}

	@Override
	public void successMessage(String string) {
		System.out.println(string);
		System.out.println("----------");
		checkContinue();
	}

	@Override
	public void failureMessage(String string) {
		System.out.println(string);
		System.out.println("---------------------");
		checkContinue();
	}

	@Override
	public long getTransferAccount() {
		System.out.print("Enter Account No -->");
		long accountNo = input.nextLong();
		input.nextLine();
		return accountNo;
	}

	@Override
	public void errorMessage(String string) {
		System.out.println(string);
		checkContinue();
	}

	@Override
	public void showHistory(List<TransactionHistory> history) {
		System.out.println("---> Transaction History <---");
		System.out.println();
		System.out.printf("%15s | %15s |%15s| %15s | %15s | %15s | %15s|%n", "CustomerId", "Detail", "From ",
				"Receiver", " Amount", "Date", "Time");
		for (TransactionHistory val : history) {
			System.out.printf("%15d | %15s | %15d | %15d | %15.2f | %15s | %15s|%n", val.getCustomerId(),
					val.getOption(), val.getFrom(), val.getTo(), val.getTransferAmount(), val.getDate().toString(),
					val.getTime().toString());
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");
		checkContinue();
	}

//----------------------------------------------for requesting loan---------------------------------------------------
	private void loanRequest() {
		System.out.println("Enter Loan Amount");
		double loanAmount = input.nextDouble();
		System.out.println("Enter your Salary");
		double salary = input.nextDouble();
		transactionControler.loanRequest(loanAmount, salary);
		System.out.println("Your Loan Request is under process, Pleace come after some times");
		System.out.println("-------------------------------------------------------");

	}
}
