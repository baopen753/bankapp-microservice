package org.baopen753.loans.repository;

import org.baopen753.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Optional<Loan> findLoanByMobileNumber(String mobileNumber);
    Optional<Loan> findLoanByLoanNumber(String loanNumber);
}
