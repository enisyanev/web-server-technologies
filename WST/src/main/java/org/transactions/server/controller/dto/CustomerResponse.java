package org.transactions.server.controller.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<AccountResponse> accounts;
}
