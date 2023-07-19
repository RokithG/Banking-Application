package com.bankingapplication.dto;

public class LoanRequest {
	private double loanAmount;
	private long customerId;
	private boolean loanRequest;
	private boolean approval;
	private double salary;
	private boolean asked;

	public LoanRequest(double loanAmount, double salary, long customerId, boolean loanRequest, boolean approval) {
		this.loanAmount = loanAmount;
		this.salary = salary;
		this.customerId = customerId;
		this.loanRequest = loanRequest;
		this.approval = approval;
		asked = true;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isAsked() {
		return asked;
	}

	public void setAsked(boolean asked) {
		this.asked = asked;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public double getCustomerSalary() {
		return salary;
	}

	public void setCustomerSalary(double customerSalary) {
		this.salary = customerSalary;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public boolean isLoanRequest() {
		return loanRequest;
	}

	public void setLoanRequest(boolean loanRequest) {
		this.loanRequest = loanRequest;
	}

}
