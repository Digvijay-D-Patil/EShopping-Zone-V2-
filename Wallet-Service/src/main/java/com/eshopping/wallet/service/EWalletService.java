package com.eshopping.wallet.service;

import java.util.List;

import com.eshopping.wallet.dto.EWalletDTO;
import com.eshopping.wallet.dto.StatementDTO;

public interface EWalletService {
	EWalletDTO addWallet(EWalletDTO wallet);

	EWalletDTO getWalletById(Long walletId);

	void addStatement(Long walletId, StatementDTO statement);

	List<StatementDTO> getStatementsByWalletId(Long walletId);

	void deleteWalletById(Long walletId);
}
