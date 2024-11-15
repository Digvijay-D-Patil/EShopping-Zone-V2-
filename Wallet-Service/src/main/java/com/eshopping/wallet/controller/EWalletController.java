package com.eshopping.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.wallet.dto.EWalletDTO;
import com.eshopping.wallet.dto.StatementDTO;
import com.eshopping.wallet.service.EWalletService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/wallets")
public class EWalletController {

	@Autowired
	private EWalletService walletService;

	@Operation(summary = "Add a new wallet")
	@PostMapping
	public ResponseEntity<EWalletDTO> addWallet(@Valid @RequestBody EWalletDTO walletDTO) {
		EWalletDTO savedWallet = walletService.addWallet(walletDTO);
		return new ResponseEntity<>(savedWallet, HttpStatus.CREATED);
	}

	@Operation(summary = "Get wallet details by wallet ID")
	@GetMapping("/{walletId}")
	public ResponseEntity<EWalletDTO> getWalletById(@PathVariable Long walletId) {
		EWalletDTO walletDTO = walletService.getWalletById(walletId);
		return ResponseEntity.ok(walletDTO);
	}

	@Operation(summary = "Add a statement to a wallet by wallet ID")
	@PostMapping("/{walletId}/statements")
	public ResponseEntity<Void> addStatement(@PathVariable Long walletId,
			@Valid @RequestBody StatementDTO statementDTO) {
		walletService.addStatement(walletId, statementDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(summary = "Get all statements for a specific wallet by wallet ID")
	@GetMapping("/{walletId}/statements")
	public ResponseEntity<List<StatementDTO>> getStatementsByWalletId(@PathVariable Long walletId) {
		List<StatementDTO> statementDTOs = walletService.getStatementsByWalletId(walletId);
		return ResponseEntity.ok(statementDTOs);
	}

	@Operation(summary = "Delete a wallet by wallet ID")
	@DeleteMapping("/{walletId}")
	public ResponseEntity<Void> deleteWallet(@PathVariable Long walletId) {
		walletService.deleteWalletById(walletId);
		return ResponseEntity.noContent().build();
	}
}
