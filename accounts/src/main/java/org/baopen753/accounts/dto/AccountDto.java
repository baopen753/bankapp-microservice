package org.baopen753.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @JsonProperty("account_number")
    @NotEmpty
    @Pattern(regexp = "(^$[0-9]{10})", message = "Account number should be 10 digits")
    private Long accountNumber;

    @JsonProperty("account_type")
    @NotEmpty(message = "Account type can not be null or empty value")
    private String accountType;

    @JsonProperty("branch_address")
    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;

}
