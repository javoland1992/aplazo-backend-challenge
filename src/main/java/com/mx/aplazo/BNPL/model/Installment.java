package com.mx.aplazo.BNPL.model;

import com.mx.aplazo.BNPL.util.InstallmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Installment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;
    private OffsetDateTime scheduledPaymentDate;

    @Enumerated(EnumType.STRING)
    private InstallmentStatus status;

}
