package com.mx.aplazo.BNPL.controller;

import com.mx.aplazo.BNPL.dto.CustomerRequest;
import com.mx.aplazo.BNPL.dto.CustomerResponse;
import com.mx.aplazo.BNPL.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.create(customerRequest);
        String location = "/v1/customers/customers/" + customerResponse.getId();
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", location).body(customerResponse);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customerResponse = customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }
}
