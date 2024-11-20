package org.transactions.server.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponse {

    private UUID id;
    private String currency;
    private BigDecimal balance;
    private UUID customerId;

}
