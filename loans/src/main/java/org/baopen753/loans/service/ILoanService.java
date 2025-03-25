package org.baopen753.loans.service;

import org.baopen753.loans.dto.LoanDto;

public interface ILoanService {

    /**
     * @param mobileNumber - Mobile number of customer
     */
    LoanDto createLoan(String mobileNumber);

    /**
     * @param mobileNumber - Input mobile number
     * @return LoanDto - LoanDetails based on mobile number
     */
    LoanDto getLoanByMobileNumber(String mobileNumber);


    /**
     * @param loanDto - LoanDto object
     * @return boolean - indicating update transaction if successful or not
     */
    boolean updateLoan(LoanDto loanDto);


    /**
     * @param mobileNumber - Mobile number of customer
     * @return boolean - indicating delete transaction if successful or not
     */
    boolean deleteLoan(String mobileNumber);
}
