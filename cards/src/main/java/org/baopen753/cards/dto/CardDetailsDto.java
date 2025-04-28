package org.baopen753.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "cards")
@Getter
@Setter
public class CardDetailsDto{
    private String message;
    private HashMap<String, String> contactDetails;
    private List<String> calls;
}