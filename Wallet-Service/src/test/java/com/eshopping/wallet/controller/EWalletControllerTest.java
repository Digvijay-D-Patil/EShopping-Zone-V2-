package com.eshopping.wallet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eshopping.wallet.dto.EWalletDTO;
import com.eshopping.wallet.dto.StatementDTO;
import com.eshopping.wallet.exception.WalletNotFoundException;
import com.eshopping.wallet.service.EWalletService;

class EWalletControllerTest {

	@InjectMocks
	private EWalletController eWalletController;

	@Mock
	private EWalletService walletService;

	private EWalletDTO walletDTO;
	private StatementDTO statementDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		walletDTO = new EWalletDTO(); // Populate with necessary fields
		statementDTO = new StatementDTO(); // Populate with necessary fields
	}

	@Test
	void testAddWallet() {
		when(walletService.addWallet(any(EWalletDTO.class))).thenReturn(walletDTO);

		ResponseEntity<EWalletDTO> response = eWalletController.addWallet(walletDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		verify(walletService).addWallet(any(EWalletDTO.class));
	}

	@Test
	void testGetWalletById_Success() {
		when(walletService.getWalletById(anyLong())).thenReturn(walletDTO);

		ResponseEntity<EWalletDTO> response = eWalletController.getWalletById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		verify(walletService).getWalletById(1L);
	}

	@Test
	void testGetWalletById_NotFound() {
		when(walletService.getWalletById(anyLong())).thenThrow(new WalletNotFoundException("Wallet not found"));

		WalletNotFoundException exception = assertThrows(WalletNotFoundException.class, () -> {
			eWalletController.getWalletById(1L);
		});

		assertEquals("Wallet not found", exception.getMessage());
	}

	@Test
	void testAddStatement_Success() {
		doNothing().when(walletService).addStatement(anyLong(), any(StatementDTO.class));

		ResponseEntity<Void> response = eWalletController.addStatement(1L, statementDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(walletService).addStatement(1L, statementDTO);
	}

	@Test
	void testGetStatementsByWalletId_Success() {
		List<StatementDTO> statementDTOs = Collections.singletonList(statementDTO);
		when(walletService.getStatementsByWalletId(anyLong())).thenReturn(statementDTOs);

		ResponseEntity<List<StatementDTO>> response = eWalletController.getStatementsByWalletId(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(1, response.getBody().size());
		verify(walletService).getStatementsByWalletId(1L);
	}

	@Test
	void testDeleteWallet_Success() {
		doNothing().when(walletService).deleteWalletById(anyLong());

		ResponseEntity<Void> response = eWalletController.deleteWallet(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(walletService).deleteWalletById(1L);
	}

	@Test
	void testDeleteWallet_NotFound() {
		doThrow(new WalletNotFoundException("Wallet not found")).when(walletService).deleteWalletById(anyLong());

		WalletNotFoundException exception = assertThrows(WalletNotFoundException.class, () -> {
			eWalletController.deleteWallet(1L);
		});

		assertEquals("Wallet not found", exception.getMessage());
	}
}