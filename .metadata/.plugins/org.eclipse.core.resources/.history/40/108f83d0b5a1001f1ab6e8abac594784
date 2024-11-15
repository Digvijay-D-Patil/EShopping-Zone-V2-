package com.eshopping.wallet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.wallet.converter.EWalletConverter;
import com.eshopping.wallet.dto.EWalletDTO;
import com.eshopping.wallet.dto.StatementDTO;
import com.eshopping.wallet.entity.EWallet;
import com.eshopping.wallet.entity.Statement;
import com.eshopping.wallet.exception.WalletNotFoundException;
import com.eshopping.wallet.repository.EWalletRepository;
import com.eshopping.wallet.repository.StatementRepository;

@Service
public class EWalletServiceImpl implements EWalletService {

	@Autowired
	private EWalletRepository walletRepository;

	@Autowired
	private StatementRepository statementRepository;

	@Override
	public EWalletDTO addWallet(EWalletDTO walletDTO) {
		// Convert DTO to Entity
		EWallet wallet = EWalletConverter.dtoToEntity(walletDTO);

		// Save the entity and convert it back to DTO for the response
		EWallet savedWallet = walletRepository.save(wallet);
		return EWalletConverter.entityToDTO(savedWallet);
	}

	@Override
	public EWalletDTO getWalletById(Long walletId) {
		// Retrieve the entity and convert it to DTO
		EWallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new WalletNotFoundException("Wallet with ID " + walletId + " not found"));
		return EWalletConverter.entityToDTO(wallet);
	}

	@Override
	public void addStatement(Long walletId, StatementDTO statementDTO) {
		// Retrieve wallet entity and convert DTO to entity
		EWallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new WalletNotFoundException("Wallet with ID " + walletId + " not found"));

		Statement statement = EWalletConverter.statementDtoToEntity(statementDTO);
		wallet.addStatement(statement);

		// Save updated wallet
		walletRepository.save(wallet);
	}

	@Override
	public List<StatementDTO> getStatementsByWalletId(Long walletId) {
		// Verify wallet existence
		if (!walletRepository.existsById(walletId)) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found");
		}

		// Retrieve statements and convert each to DTO
		List<Statement> statements = statementRepository.findByWallet_WalletId(walletId);
		return statements.stream().map(EWalletConverter::statementEntityToDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteWalletById(Long walletId) {
		// Verify wallet existence before deletion
		if (!walletRepository.existsById(walletId)) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found");
		}
		walletRepository.deleteById(walletId);
	}
}
