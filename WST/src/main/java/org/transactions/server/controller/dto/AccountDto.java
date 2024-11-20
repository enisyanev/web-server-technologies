package org.transactions.server.controller.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDto {

    @NotNull(message = "customerId is mandatory")
    private UUID customerId;
    @NotNull(message = "customerId is mandatory")
    @Size(min = 3, max = 3, message = "currency should be 3 character")
    private String currency;

}
