package org.transactions.server.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDto {

    @NotNull(message = "customerId is mandatory")
    private UUID customerId;
    @NotNull(message = "accountId is mandatory")
    private UUID accountId;
    @NotNull(message = "amount is mandatory")
    private BigDecimal amount;

}
