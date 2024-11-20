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
import org.transactions.server.controller.dto.CustomerDto;
import org.transactions.server.controller.dto.CustomerResponse;
import org.transactions.server.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
@Tag(name = "Customer API", description = "Manager Customer entities")
public class CustomerController {

    private final CustomerService customerService;
    private final DtoMapper dtoMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieves customer by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer found and returned successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
    })
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(dtoMapper.toResponse(customerService.get(id)));
    }

    @PostMapping
    @Operation(summary = "Create customer", description = "Creates and stores customer")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Customer is stored successfully"),
        @ApiResponse(responseCode = "400", description = "Required fields are invalid")
    })
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerDto customer) {
        return new ResponseEntity<>(dtoMapper.toResponse(customerService.create(customer)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update customer", description = "Updates customer")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer is updated successfully"),
        @ApiResponse(responseCode = "400", description = "Required fields are invalid"),
        @ApiResponse(responseCode = "404", description = "Customer does not exist")
    })
    public ResponseEntity<CustomerResponse> update(@PathVariable("id") UUID id, @Valid @RequestBody CustomerDto customer) {
        return ResponseEntity.ok(dtoMapper.toResponse(customerService.update(id, customer)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer", description = "Deletes customer")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer is deleted successfully")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
