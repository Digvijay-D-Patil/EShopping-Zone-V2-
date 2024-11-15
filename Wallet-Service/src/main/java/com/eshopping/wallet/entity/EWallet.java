package com.eshopping.wallet.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class EWallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long walletId;

	private Double balance;

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Statement> statements = new ArrayList<>();

	public EWallet() {
	}

	public EWallet(Double balance) {
		this.balance = balance;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void addStatement(Statement statement) {
		statements.add(statement);
		statement.setWallet(this);
	}

	public void removeStatement(Statement statement) {
		statements.remove(statement);
		statement.setWallet(null);
	}
}
