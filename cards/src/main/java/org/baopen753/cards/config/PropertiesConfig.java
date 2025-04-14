package org.baopen753.cards.config;

import org.baopen753.cards.dto.CardDetailsDto;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = CardDetailsDto.class)
public class PropertiesConfig {

}

