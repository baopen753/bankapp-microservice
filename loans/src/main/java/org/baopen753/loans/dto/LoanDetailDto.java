package org.baopen753.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "loans")
public record LoanDetailDto(String message, HashMap<String, String> contactDetails, List<String> calls) {
}
