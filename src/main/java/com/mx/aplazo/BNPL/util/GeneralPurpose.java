package com.mx.aplazo.BNPL.util;

import com.mx.aplazo.BNPL.exception.InvalidAgeException;
import com.mx.aplazo.BNPL.exception.InvalidCreditLineException;
import com.mx.aplazo.BNPL.exception.InvalidDateInput;
import com.mx.aplazo.BNPL.exception.NotFoundCustomerException;
import com.mx.aplazo.BNPL.model.Customer;
import com.mx.aplazo.BNPL.model.Installment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.IntFunction;

public class GeneralPurpose {
    public static String removeLastSegment(String uri) {
        if (uri == null || uri.isEmpty()) {
            return uri;
        }

        int lastSlashIndex = uri.lastIndexOf('/');

        if (lastSlashIndex == -1) {
            return ""; // Or handle as needed, if there are no slashes
        }

        return uri.substring(0, lastSlashIndex);
    }

    public static UUID converStringToUUID(String customerId) {
        try {
            return UUID.fromString(customerId);
        } catch (IllegalArgumentException e) {
            throw new NotFoundCustomerException("customerId is not match");
        }
    }

    public static BigDecimal getCreditLine(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        int age = Period.between(birthDate, now).getYears();
        IntFunction<BigDecimal> getCreditLine = num -> {
            if (num >=18 && num <= 25) {
                return BigDecimal.valueOf(3000);
            } else if (num >=26 && num <= 30) {
                return BigDecimal.valueOf(5000);
            } else if (num >=31 && num <= 65) {
                return BigDecimal.valueOf(8000);
            } else {
                throw new InvalidAgeException("customer not eligibilite");
            }
        };
        return getCreditLine.apply(age);
    }

    public static LocalDate validateInputDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(date,formatter);
            return birthDate;
        } catch (DateTimeParseException e) {
            throw new InvalidDateInput("invalid date format, must be yyyy-MM-dd");
        }
    }

    public static BigDecimal validateCreditLine(BigDecimal amount, BigDecimal creditLineAvailable) {
        if (amount.compareTo(creditLineAvailable) < 0) {
            return amount;
        } else {
            throw new InvalidCreditLineException("no enough credit line available");
        }

    }

    public static BigDecimal getInterestRate(Customer customer) {
        if(customer.getFirstName().matches("^[CLH].*")){
            return BigDecimal.valueOf(0.13);
        }else{
            return BigDecimal.valueOf(0.16);
        }
    }

    public static List<Installment> generateInstallments(BigDecimal loanTotalAmount) {
        BigDecimal installmentAmount = loanTotalAmount.divide(BigDecimal.valueOf(5), 2, RoundingMode.HALF_UP);
        OffsetDateTime paymentDate = OffsetDateTime.now().plus(Period.ofWeeks(2));
        List<Installment> installments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Installment installment = new Installment();
            installment.setAmount(installmentAmount);
            installment.setStatus(InstallmentStatus.NEXT);
            installment.setScheduledPaymentDate(paymentDate);
            installments.add(installment);
            paymentDate = paymentDate.plus(Period.ofWeeks(2 + ((i+1) * 2)));
        }
        return installments;
    }
}
