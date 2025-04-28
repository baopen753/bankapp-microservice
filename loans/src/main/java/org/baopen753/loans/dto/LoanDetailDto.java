package org.baopen753.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "loans")
@Getter
@Setter
public class LoanDetailDto {
    private String message;
    private HashMap<String, String> contactDetails;
    private List<String> calls;
}
