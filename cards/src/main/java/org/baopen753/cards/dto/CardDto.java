package org.baopen753.cards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardDto {

    @JsonProperty("mobile_number")
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @JsonProperty("card_number")
    @NotEmpty(message = "Card number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Card number must be 10 digits")
    private String cardNumber;

    @JsonProperty("card_type")
    @NotEmpty(message = "Card type can not be null or empty")
    private String cardType;

    @JsonProperty("total_limit")
    @Positive(message = "Total card limit should be greater than zero")
    private Integer totalLimit;

    @JsonProperty("amount_used")
    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private Integer amountUsed;

    @JsonProperty("available_amount")
    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private Integer availableAmount;
}
