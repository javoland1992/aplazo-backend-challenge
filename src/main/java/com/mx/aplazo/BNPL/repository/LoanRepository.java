package com.mx.aplazo.BNPL.repository;

import com.mx.aplazo.BNPL.model.Loan;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
}
