package com.mx.aplazo.BNPL.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String secondLastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private BigDecimal creditLine;
    private BigDecimal creditLineAvailable;
    private OffsetDateTime dateOfCreation;

    public Customer(){
    }

    public Customer(String firstName, String lastName, String secondLastName, LocalDate dateOfBirth, BigDecimal creditLine, BigDecimal creditLineAvailable, OffsetDateTime dateOfCreation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.dateOfBirth = dateOfBirth;
        this.creditLine = creditLine;
        this.creditLineAvailable = creditLineAvailable;
        this.dateOfCreation = dateOfCreation;
    }


}
