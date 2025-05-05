package com.mx.aplazo.BNPL.controller;

import com.mx.aplazo.BNPL.dto.CustomerRequest;
import com.mx.aplazo.BNPL.dto.CustomerResponse;
import com.mx.aplazo.BNPL.dto.LoanRequest;
import com.mx.aplazo.BNPL.dto.LoanResponse;
import com.mx.aplazo.BNPL.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanRequest loanRequest) {
        LoanResponse loanResponse = loanService.create(loanRequest);
        String location = "/v1/loans/" + loanResponse.getId();
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", location).body(loanResponse);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponse> getCustomerById(@PathVariable String loanId) {
        LoanResponse loanResponse = loanService.getLoanById(loanId);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponse);
    }
}
