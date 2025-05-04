package com.mx.aplazo.BNPL.model;

import com.mx.aplazo.BNPL.util.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    private OffsetDateTime createdAt;

    private BigDecimal loanAmount;

    private BigDecimal interestRate;

    private BigDecimal commissionAmount;

    private BigDecimal loanTotalAmount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Installment> installments;

    public Loan(Customer customer, String loanStatus, OffsetDateTime createdAt, BigDecimal loanAmount,
                BigDecimal interestRate, BigDecimal commissionAmount, BigDecimal loanTotalAmount,
                List<Installment> installments) {
        this.customer = customer;
        this.loanStatus = LoanStatus.valueOf(loanStatus);
        this.createdAt = createdAt;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.commissionAmount = commissionAmount;
        this.loanTotalAmount = loanTotalAmount;
        this.installments = installments;
    }
}
