package com.mx.aplazo.BNPL.controller;

import com.mx.aplazo.BNPL.dto.CustomerRequest;
import com.mx.aplazo.BNPL.dto.CustomerResponse;
import com.mx.aplazo.BNPL.dto.LoanRequest;
import com.mx.aplazo.BNPL.dto.LoanResponse;
import com.mx.aplazo.BNPL.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "LoanController")
@RequestMapping("/v1/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @Operation(summary = "Create a loan, validating data input", description = "Here you can create a loan, validating the data input")
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanRequest loanRequest) {
        LoanResponse loanResponse = loanService.create(loanRequest);
        String location = "/v1/loans/" + loanResponse.getId();
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", location).body(loanResponse);
    }

    @GetMapping("/{loanId}")
    @Operation(summary = "Get loan using a loanId (UUID)", description = "Here you can get a loan using a loanId (UUID)")
    public ResponseEntity<LoanResponse> getCustomerById(@PathVariable String loanId) {
        LoanResponse loanResponse = loanService.getLoanById(loanId);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponse);
    }
}
