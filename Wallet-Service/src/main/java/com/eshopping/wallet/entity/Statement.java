package com.eshopping.wallet.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Statement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statementId;

	private String transactionType;
	private Double amount;
	private LocalDateTime dateTime;
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private EWallet wallet;

	public Statement() {
	}

	public Statement(String transactionType, Double amount, LocalDateTime dateTime, String remarks) {
		this.transactionType = transactionType;
		this.amount = amount;
		this.dateTime = dateTime;
		this.remarks = remarks;
	}

	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public EWallet getWallet() {
		return wallet;
	}

	public void setWallet(EWallet wallet) {
		this.wallet = wallet;
	}
}
