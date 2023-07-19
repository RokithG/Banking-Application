package com.bankingapplication.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionHistory {

	private long customerId;
	private double transferAmount;
	private String option;
	private long from;
	private long to;
	private LocalDate date;
	private LocalTime time;

	public TransactionHistory(long customerId, double transferAmount, String option, long from, long to, LocalDate date,
			LocalTime time) {
		this.customerId = customerId;
		this.transferAmount = transferAmount;
		this.option = option;
		this.setFrom(from);
		this.to = to;
		this.date = date;
		this.time = time;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

}
