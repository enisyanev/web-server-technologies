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
import org.transactions.server.controller.dto.TransactionDetailsDto;
import org.transactions.server.controller.dto.TransactionDetailsResponse;
import org.transactions.server.service.TransactionDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/transaction-details")
@Tag(name = "Transaction Details API", description = "Manager Transaction Details entities")
public class TransactionDetailsController {

    private final TransactionDetailsService transactionDetailsService;
    private final DtoMapper dtoMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction details by ID", description = "Retrieves transaction details by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction details found and returned successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction details not found"),
    })
    public ResponseEntity<TransactionDetailsResponse> getTransactionDetails(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(dtoMapper.toResponse(transactionDetailsService.get(id)));
    }

    @PostMapping
    @Operation(summary = "Create transaction details", description = "Creates and stores transaction details")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Transaction details are stored successfully"),
        @ApiResponse(responseCode = "400", description = "Transaction details fields are invalid"),
        @ApiResponse(responseCode = "404", description = "Transaction does not exist")
    })
    public ResponseEntity<TransactionDetailsResponse> createTransactionDetails(@RequestBody TransactionDetailsDto transactionDetails) {
        return new ResponseEntity<>(dtoMapper.toResponse(transactionDetailsService.create(transactionDetails)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates transaction details", description = "Update transaction details")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction details are successfully updated"),
        @ApiResponse(responseCode = "400", description = "Transaction details fields are invalid"),
        @ApiResponse(responseCode = "404", description = "Transaction details not found"),
    })
    public ResponseEntity<TransactionDetailsResponse> updateTransactionDetails(@PathVariable("id") UUID id,
        @RequestBody TransactionDetailsDto transactionDetails) {
        return ResponseEntity.ok(dtoMapper.toResponse(transactionDetailsService.update(id, transactionDetails)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaction details", description = "Deletes transaction details")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction details are deleted successfully")
    })
    public ResponseEntity<Void> deleteTransactionDetails(@PathVariable("id") UUID id) {
        transactionDetailsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
