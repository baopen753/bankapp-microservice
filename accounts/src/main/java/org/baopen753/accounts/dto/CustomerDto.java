package org.baopen753.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @JsonProperty("name")
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer's name should be between 5-30")
    private String name;

    @JsonProperty("email")
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @JsonProperty("mobile_phone")
    @Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    private AccountDto accountDto;

}
