package com.eshopping.wallet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eshopping.wallet.dto.EWalletDTO;
import com.eshopping.wallet.dto.StatementDTO;
import com.eshopping.wallet.entity.EWallet;
import com.eshopping.wallet.entity.Statement;
import com.eshopping.wallet.exception.WalletNotFoundException;
import com.eshopping.wallet.repository.EWalletRepository;
import com.eshopping.wallet.repository.StatementRepository;

class EWalletServiceImplTest {

	@InjectMocks
	private EWalletServiceImpl eWalletService;

	@Mock
	private EWalletRepository walletRepository;

	@Mock
	private StatementRepository statementRepository;

	private EWalletDTO walletDTO;
	private EWallet wallet;
	private StatementDTO statementDTO;
	private Statement statement;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		walletDTO = new EWalletDTO(); // Populate with necessary fields
		wallet = new EWallet(); // Populate with necessary fields
		statementDTO = new StatementDTO(); // Populate with necessary fields
		statement = new Statement(); // Populate with necessary fields
	}

	@Test
	void testGetWalletById_Success() {
		when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet));
		EWalletDTO result = eWalletService.getWalletById(1L);
		assertNotNull(result);
		verify(walletRepository).findById(1L);
	}

	@Test
	void testGetWalletById_NotFound() {
		when(walletRepository.findById(anyLong())).thenReturn(Optional.empty());
		WalletNotFoundException exception = assertThrows(WalletNotFoundException.class, () -> {
			eWalletService.getWalletById(1L);
		});
		assertEquals("Wallet with ID 1 not found", exception.getMessage());
	}

	@Test
	void testAddStatement_Success() {
		when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet));
		eWalletService.addStatement(1L, statementDTO);
		verify(walletRepository).save(any(EWallet.class));
	}

	@Test
	void testAddStatement_WalletNotFound() {
		when(walletRepository.findById(anyLong())).thenReturn(Optional.empty());
		WalletNotFoundException exception = assertThrows(WalletNotFoundException.class, () -> {
			eWalletService.addStatement(1L, statementDTO);
		});
		assertEquals("Wallet with ID 1 not found", exception.getMessage());
	}

	@Test
	void testGetStatementsByWalletId_Success() {
		when(walletRepository.existsById(anyLong())).thenReturn(true);
		when(statementRepository.findByWallet_WalletId(anyLong())).thenReturn(Collections.singletonList(statement));
		List<StatementDTO> result = eWalletService.getStatementsByWalletId(1L);
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(statementRepository).findByWallet_WalletId(1L);
	}

	@Test
	void testGetStatementsByWalletId_NotFound() {
		when(walletRepository.existsById(anyLong())).thenReturn(false);
		WalletNotFoundException exception = assertThrows(WalletNotFoundException.class, () -> {
			eWalletService.getStatementsByWalletId(1L);
		});
		assertEquals("Wallet with ID 1 not found", exception.getMessage());
	}

	@Test
	void testDeleteWalletById_Success() {
		when(walletRepository.existsById(anyLong())).thenReturn(true);
		eWalletService.deleteWalletById(1L);
		verify(walletRepository).deleteById(1L);
	}

	@Test
	void testDeleteWalletById_NotFound() {
		when(walletRepository.existsById(anyLong())).thenReturn(false);
		WalletNotFoundException exception = assertThrows(WalletNotFoundException.class, () -> {
			eWalletService.deleteWalletById(1L);
		});
	}
}