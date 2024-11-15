package com.eshopping.wallet.converter;

import java.util.stream.Collectors;

import com.eshopping.wallet.dto.EWalletDTO;
import com.eshopping.wallet.dto.StatementDTO;
import com.eshopping.wallet.entity.EWallet;
import com.eshopping.wallet.entity.Statement;

public class EWalletConverter {

	// Method to convert EWallet entity to EWalletDTO
	public static EWalletDTO entityToDTO(EWallet wallet) {
		EWalletDTO walletDTO = new EWalletDTO();
		walletDTO.setWalletId(wallet.getWalletId());
		walletDTO.setBalance(wallet.getBalance());
		walletDTO.setStatements(wallet.getStatements().stream().map(EWalletConverter::statementEntityToDTO)
				.collect(Collectors.toList()));
		return walletDTO;
	}

	// Method to convert EWalletDTO to EWallet entity
	public static EWallet dtoToEntity(EWalletDTO walletDTO) {
		EWallet wallet = new EWallet();
		wallet.setWalletId(walletDTO.getWalletId());
		wallet.setBalance(walletDTO.getBalance());
		wallet.setStatements(walletDTO.getStatements().stream().map(EWalletConverter::statementDtoToEntity)
				.collect(Collectors.toList()));
		return wallet;
	}

	// Method to convert Statement entity to StatementDTO
	public static StatementDTO statementEntityToDTO(Statement statement) {
		StatementDTO statementDTO = new StatementDTO();
		statementDTO.setStatementId(statement.getStatementId());
		statementDTO.setTransactionType(statement.getTransactionType());
		statementDTO.setAmount(statement.getAmount());
		statementDTO.setDateTime(statement.getDateTime());
		statementDTO.setRemarks(statement.getRemarks());
		return statementDTO;
	}

	// Method to convert StatementDTO to Statement entity
	public static Statement statementDtoToEntity(StatementDTO statementDTO) {
		Statement statement = new Statement();
		statement.setStatementId(statementDTO.getStatementId());
		statement.setTransactionType(statementDTO.getTransactionType());
		statement.setAmount(statementDTO.getAmount());
		statement.setDateTime(statementDTO.getDateTime());
		statement.setRemarks(statementDTO.getRemarks());
		return statement;
	}
}
