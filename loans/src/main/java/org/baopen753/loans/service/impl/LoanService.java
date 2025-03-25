package org.baopen753.loans.service.impl;

import lombok.AllArgsConstructor;
import org.baopen753.loans.constant.LoanConstant;
import org.baopen753.loans.dto.LoanDto;
import org.baopen753.loans.entity.Loan;
import org.baopen753.loans.exception.LoanAccountAlreadyExistException;
import org.baopen753.loans.exception.ObjectNotFoundException;
import org.baopen753.loans.mapper.LoanMapper;
import org.baopen753.loans.repository.LoanRepository;
import org.baopen753.loans.service.ILoanService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;

    /**
     * @param mobileNumber - Mobile number of customer
     */
    @Override
    public LoanDto createLoan(String mobileNumber) {
        Optional<Loan> loanInDb = loanRepository.findLoanByMobileNumber(mobileNumber);

        if (loanInDb.isPresent())
            throw new LoanAccountAlreadyExistException(String.format("Loan with mobile number '%s' already exist", mobileNumber));

        Loan newLoan = createLoanAccount(mobileNumber);
        loanRepository.save(newLoan);

        return LoanMapper.mapToLoanDto(newLoan, new LoanDto());
    }


    private Loan createLoanAccount(String mobileNumber) {
        Loan newLoan = new Loan();
        Long randomLoanNumber = 100_000_000_000L + new Random().nextInt(900_000_000);

        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanNumber(String.valueOf(randomLoanNumber));
        newLoan.setLoanType(LoanConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
        newLoan.setCreatedAt(LocalDateTime.now());
        newLoan.setCreateBy("BaoPen");

        return newLoan;
    }

    /**
     * @param mobileNumber - Input mobile number
     * @return LoanDto - LoanDetails based on mobile number
     */
    @Override
    public LoanDto getLoanByMobileNumber(String mobileNumber) {
        Optional<Loan> loanAccountInDb = loanRepository.findLoanByMobileNumber(mobileNumber);

        if (loanAccountInDb.isEmpty())
            throw new ObjectNotFoundException("Loan", "mobile_number", mobileNumber);

        return LoanMapper.mapToLoanDto(loanAccountInDb.get(), new LoanDto());
    }

    /**
     * @param loanDto - LoanDto object
     * @return boolean - indicating update transaction if successful or not
     */
    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan loanInDb = loanRepository.findLoanByLoanNumber(loanDto.getLoanNumber()).orElseThrow(() ->
                new ObjectNotFoundException("Loan", "loan_number", loanDto.getLoanNumber()));
        LoanMapper.mapToLoan(loanDto, loanInDb);
        loanRepository.save(loanInDb);
        return true;
    }


    /**
     * @param mobileNumber - Mobile number of customer
     * @return boolean - indicating delete transaction if successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loanInDb = loanRepository.findLoanByMobileNumber(mobileNumber).orElseThrow(() ->
                new ObjectNotFoundException("Loan", "mobile_number", mobileNumber));
        loanRepository.deleteById(loanInDb.getLoanId());
        return true;
    }
}
