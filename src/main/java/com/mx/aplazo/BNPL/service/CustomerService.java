package com.mx.aplazo.BNPL.service;

import com.mx.aplazo.BNPL.dto.CustomerRequest;
import com.mx.aplazo.BNPL.dto.CustomerResponse;
import com.mx.aplazo.BNPL.exception.NotFoundCustomerException;
import com.mx.aplazo.BNPL.model.Customer;
import com.mx.aplazo.BNPL.repository.CustomerRepository;
import com.mx.aplazo.BNPL.util.GeneralPurpose;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerResponse create(CustomerRequest customerRequest) {
        LocalDate birthDate = GeneralPurpose.validateInputDate(customerRequest.getDateOfBirth());
        BigDecimal creditLine = GeneralPurpose.getCreditLine(birthDate).setScale(2, RoundingMode.HALF_UP);

        Customer customer = new Customer(
                customerRequest.getFirstName(),
                customerRequest.getLastName(),
                customerRequest.getSecondLastName(),
                birthDate,
                creditLine,
                creditLine,
                OffsetDateTime.now()
        );

        customerRepository.save(customer);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(String.valueOf(customer.getId()));
        customerResponse.setCreditLineAmount(creditLine);
        customerResponse.setAvailableCreditLineAmount(creditLine);
        customerResponse.setCreatedAt(customerRequest.getDateOfBirth());
        return customerResponse;
    }

    @Transactional
    public CustomerResponse getCustomerById(String customerId){
        UUID customerUUID = GeneralPurpose.converCustomerIdToUUID(customerId);
        Optional<Customer> clientOptional = customerRepository.findById(customerUUID);
        if (clientOptional.isPresent()) {
            Customer customer = clientOptional.get();
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(String.valueOf(customer.getId()));
            customerResponse.setCreditLineAmount(customer.getCreditLine());
            customerResponse.setAvailableCreditLineAmount(customer.getCreditLineAvailable());
            customerResponse.setCreatedAt(String.valueOf(customer.getDateOfCreation()));
            return customerResponse;
        } else {
            throw new NotFoundCustomerException("customerId is not match");
        }
    }

    public List<CustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponse;
        customerResponse = customers.stream().map(customer -> {
            CustomerResponse response = new CustomerResponse();
            response.setId(String.valueOf(customer.getId()));
            response.setCreditLineAmount(customer.getCreditLine());
            response.setAvailableCreditLineAmount(customer.getCreditLineAvailable());
            response.setCreatedAt(String.valueOf(customer.getDateOfCreation()));
            return response;
        }).toList();
        return customerResponse;
    }
}
