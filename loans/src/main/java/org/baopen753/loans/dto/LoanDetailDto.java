package org.baopen753.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "loans")
public class LoanDetailDto {
    private String message;
    private HashMap<String, String> contactDetails;
    private List<String> calls;
}
