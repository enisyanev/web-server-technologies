package org.transactions.server.controller.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {

    private UUID id;
    private UUID customerId;
    private UUID accountId;
    private BigDecimal amount;
    private String currency;
    private OffsetDateTime insertTime;
    private TransactionDetailsResponse details;
}
