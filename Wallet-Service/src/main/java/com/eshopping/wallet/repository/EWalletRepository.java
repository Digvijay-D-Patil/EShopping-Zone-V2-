package com.eshopping.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.wallet.entity.EWallet;

public interface EWalletRepository extends JpaRepository<EWallet, Long> {
}
