package org.baopen753.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "cards")
public record CardDetailsDto(String message, HashMap<String, String> contactDetails, List<String> calls){

}