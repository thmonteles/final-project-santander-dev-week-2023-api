package com.thmonteles.controller;

import com.thmonteles.controller.dto.TransactionDto;
import com.thmonteles.domain.model.Transaction;
import com.thmonteles.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions Controller", description = "RESTful API for managing transactions.")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @Operation(summary = "Get all transactions", description = "Retrieve a list of all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<TransactionDto>> findAll() {
        var transactions = transactionService.findAll();
        var transactionsDto = transactions.stream().map(TransactionDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(transactionsDto);
    }

    @PostMapping
    @Operation(summary = "Create a new transaction", description = "Create a new transaction and return the created transaction's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid transaction data provided")
    })
    public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto transactionDto) {
        var transaction = transactionService.create(transactionDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transaction.getId())
                .toUri();
        return ResponseEntity.created(location).body(new TransactionDto(transaction));
    }

}
