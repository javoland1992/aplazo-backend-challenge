package com.mx.aplazo.BNPL.repository;

import com.mx.aplazo.BNPL.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
