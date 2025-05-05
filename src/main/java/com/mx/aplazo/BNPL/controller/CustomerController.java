package com.mx.aplazo.BNPL.controller;

import com.mx.aplazo.BNPL.dto.CustomerRequest;
import com.mx.aplazo.BNPL.dto.CustomerResponse;
import com.mx.aplazo.BNPL.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "CustomerController")
@RequestMapping("/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Create a customer, validating data input", description = "Here you can create a customer, validating the data input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer successfully created",
                    headers = @Header(name = "Location", description = "Relative path to search for newly created customer", schema = @Schema(type = "string")),
                    content = @Content(schema = @Schema(implementation = CustomerRequest.class)
                            , examples = @ExampleObject(value = """
                            {
                                 "id": "cde1581f-df97-4054-9a2a-ec68cfaed11b",
                                 "createdAt": "1998-07-21",
                                 "creditLineAmount": 5000.00,
                                 "availableCreditLineAmount": 5000.00
                            }
                        """))),
            @ApiResponse(responseCode = "400", description = "Customer attribute is null or blank",
                    content = @Content(schema = @Schema(implementation = CustomerRequest.class)
                            , examples = @ExampleObject(value = """
                            {
                                "code": "APZ000002",
                                "error": "INVALID_CUSTOMER_REQUEST",
                                "timestamp": 1746403268,
                                "message": {
                                    "secondLastName": "is null",
                                    "firstName": "is blank"
                                },
                                "path": "/v1/customers"
                            }
                        """)))
    })
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.create(customerRequest);
        String location = "/v1/customers/" + customerResponse.getId();
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", location).body(customerResponse);
    }


    @GetMapping("/{customerId}")
    @Operation(summary = "Get customer using a customerId (UUID)",
            description = "Here you can get a customer using a customerId (UUID)",
    tags = {"Customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class)
                            , examples = @ExampleObject(value = """
                            {
                                 "id": "cde1581f-df97-4054-9a2a-ec68cfaed11b",
                                 "createdAt": "1998-07-21",
                                 "creditLineAmount": 5000.00,
                                 "availableCreditLineAmount": 5000.00
                            }
                        """))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(schema = @Schema(implementation = CustomerRequest.class)
                            , examples = @ExampleObject(value = """
                            {
                                "code": "APZ000002",
                                "error": "NOT_FOUND_CUSTOMER",
                                "timestamp": 1746403268,
                                "message": {
                                    "customerId": "is not match"
                                },
                                "path": "/v1/customers/{customerId}"
                            }
                        """)))
    })
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @GetMapping
    @Operation(summary = "Get all customers", description = "Here you can get all customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customerResponse = customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }
}
