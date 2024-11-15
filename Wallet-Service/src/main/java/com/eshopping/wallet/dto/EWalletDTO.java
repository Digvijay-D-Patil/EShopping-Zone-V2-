package com.eshopping.wallet.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EWalletDTO {

	@NotNull(message = "Wallet ID cannot be null")
	private Long walletId;

	@NotNull(message = "Balance cannot be null")
	@Min(value = 0, message = "Balance cannot be negative")
	private Double balance;

	@NotNull(message = "Statements list cannot be null")
	private List<@NotNull StatementDTO> statements;
}
