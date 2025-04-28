package org.baopen753.accounts.dto;

import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountDetailsDto {
    private String message;
    private HashMap<String, String> contactDetails;
    private List<String> calls;
}