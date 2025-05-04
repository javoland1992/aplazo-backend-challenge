package com.mx.aplazo.BNPL.service;

import com.mx.aplazo.BNPL.dto.*;
import com.mx.aplazo.BNPL.exception.NotFoundCustomerException;
import com.mx.aplazo.BNPL.model.Customer;
import com.mx.aplazo.BNPL.model.Installment;
import com.mx.aplazo.BNPL.model.Loan;
import com.mx.aplazo.BNPL.repository.CustomerRepository;
import com.mx.aplazo.BNPL.repository.LoanRepository;
import com.mx.aplazo.BNPL.util.GeneralPurpose;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public LoanService(LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public LoanResponse create(LoanRequest loanRequest) {
        UUID customerUUID = GeneralPurpose.converStringToUUID(loanRequest.getCustomerId());
        Customer customer = customerRepository.findById(customerUUID)
                .orElseThrow(() -> new NotFoundCustomerException("customerId is not match"));
        BigDecimal loanAmount = GeneralPurpose.validateCreditLine(loanRequest.getAmount(), customer.getCreditLineAvailable());

        BigDecimal interestRate = GeneralPurpose.getInterestRate(customer);

        BigDecimal commissionAmount = loanAmount.multiply(interestRate).setScale(2, RoundingMode.HALF_UP);

        BigDecimal loanTotalAmount = loanAmount.add(commissionAmount);

        List<Installment> installments= GeneralPurpose.generateInstallments(loanTotalAmount);

        Loan loan = new Loan(
                customer,
                "ACTIVE",
                OffsetDateTime.now(),
                loanAmount,
                interestRate,
                commissionAmount,
                loanTotalAmount,
                installments
                );
        loanRepository.save(loan);

        customer.setCreditLineAvailable(customer.getCreditLineAvailable().subtract(loanAmount));
        customerRepository.saveAndFlush(customer);

        InstallmentResponse installmentResponse = new InstallmentResponse();
        installmentResponse.setInstallments(installments);

        PaymentPlan paymentPlan = new PaymentPlan();
        paymentPlan.setCommissionAmount(loan.getCommissionAmount());
        paymentPlan.setInstallmentResponse(installmentResponse);

        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setId(String.valueOf(loan.getId()));
        loanResponse.setCustomerId(String.valueOf(loan.getCustomer().getId()));
        loanResponse.setEstatus(String.valueOf(loan.getLoanStatus()));
        loanResponse.setCreatedAt(String.valueOf(loan.getCreatedAt()));
        loanResponse.setPaymentPlan(paymentPlan);

        return loanResponse;


    }


}
