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
import org.transactions.server.controller.dto.TransactionDto;
import org.transactions.server.controller.dto.TransactionResponse;
import org.transactions.server.entity.Transaction;
import org.transactions.server.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction API", description = "Manager Transaction entities")
public class TransactionController {

    private final TransactionService transactionService;
    private final DtoMapper dtoMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID", description = "Retrieves transaction by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction found and returned successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found"),
    })
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(dtoMapper.toResponse(transactionService.get(id)));
    }

    @PostMapping
    @Operation(summary = "Create transaction", description = "Creates and stores transaction and increases balance")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Transaction is stored successfully"),
        @ApiResponse(responseCode = "400", description = "Transaction fields are invalid"),
        @ApiResponse(responseCode = "404", description = "Customer does not exist"),
        @ApiResponse(responseCode = "404", description = "Account does not exist"),
    })
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionDto transaction) {
        return new ResponseEntity<>(dtoMapper.toResponse(transactionService.create(transaction)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates transaction", description = "Update amount of transaction")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Amount and balance are successfully updated"),
        @ApiResponse(responseCode = "400", description = "Transaction fields are invalid"),
        @ApiResponse(responseCode = "400", description = "Transaction not found"),
    })
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable("id") UUID id, @RequestBody TransactionDto transaction) {
        return ResponseEntity.ok(dtoMapper.toResponse(transactionService.update(id, transaction)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaction", description = "Deletes transaction and decreases account balance")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction is deleted successfully")
    })
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable("id") UUID id) {
        transactionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
