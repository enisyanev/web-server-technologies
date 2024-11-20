package org.transactions.server.controller.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDetailsDto {

    @NotNull(message = "transactionId is mandatory")
    private UUID transactionId;
    @NotBlank(message = "type is mandatory")
    private String type;
    @NotBlank(message = "message is mandatory")
    private String message;
    @NotBlank(message = "source is mandatory")
    private String source;

}
