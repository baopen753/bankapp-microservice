package org.baopen753.loans.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
public class LoanDto {
    @JsonProperty("mobile_number")
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @JsonProperty("loan_number")
    @NotEmpty(message = "Loan number can not be null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    private String loanNumber;

    @JsonProperty("loan_type")
    @NotEmpty(message = "Loan type can not be null or empty")
    private String loanType;

    @JsonProperty("total_loan")
    @Positive(message = "Total loan must be a positive value")
    private Integer totalLoan;

    @JsonProperty("amount_paid")
    @PositiveOrZero(message = "Amount paid must be equal or greater than 0")
    private Integer amountPaid;

    @JsonProperty("outstanding_amount")
    @PositiveOrZero(message = "Amount paid must be equal or greater than 0")
    private Integer outstandingAmount;
}
