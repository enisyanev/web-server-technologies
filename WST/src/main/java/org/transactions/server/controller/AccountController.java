package org.transactions.server.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.transactions.server.component.DtoMapper;
import org.transactions.server.controller.dto.AccountDto;
import org.transactions.server.controller.dto.AccountResponse;
import org.transactions.server.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts API", description = "Manager Account entities")
public class AccountController {

    private final AccountService accountService;
    private final DtoMapper dtoMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get Account by ID", description = "Returns account with the given ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Account found and returned successfully"),
        @ApiResponse(responseCode = "404", description = "Account not found"),
    })
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(dtoMapper.toResponse(accountService.get(id)));
    }

    @PostMapping
    @Operation(summary = "Create account", description = "Creates and stores account")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Account is stored successfully"),
        @ApiResponse(responseCode = "400", description = "Account fields are invalid"),
        @ApiResponse(responseCode = "404", description = "Customer does not exist")
    })
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountDto account) {
        return new ResponseEntity<>(dtoMapper.toResponse(accountService.create(account)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account", description = "Updates account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Account is updated successfully"),
        @ApiResponse(responseCode = "400", description = "Required fields are invalid"),
        @ApiResponse(responseCode = "404", description = "Account does not exist")
    })
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable("id") UUID id, @RequestBody AccountDto account) {
        return ResponseEntity.ok(dtoMapper.toResponse(accountService.update(id, account)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account", description = "Deletes account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Account is deleted successfully")
    })
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") UUID id) {
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
