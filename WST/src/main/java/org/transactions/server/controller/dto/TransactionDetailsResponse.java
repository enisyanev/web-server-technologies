package org.transactions.server.controller.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDetailsResponse {

    private UUID id;
    private String type;
    private String message;
    private String source;

}
