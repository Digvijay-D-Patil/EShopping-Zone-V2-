package com.eshopping.wallet.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatementDTO {

	@NotNull(message = "Statement ID cannot be null")
	private Long statementId;

	@NotBlank(message = "Transaction type cannot be blank")
	private String transactionType;

	@NotNull(message = "Amount cannot be null")
	@Min(value = 0, message = "Amount cannot be negative")
	private Double amount;

	@NotNull(message = "Date and time cannot be null")
	private LocalDateTime dateTime;

	@NotBlank(message = "Remarks cannot be blank")
	private String remarks;
}
