package com.eshopping.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.wallet.entity.Statement;

public interface StatementRepository extends JpaRepository<Statement, Long> {
	List<Statement> findByWallet_WalletId(Long walletId);
}
