package org.baopen753.accounts.config;

import org.baopen753.accounts.dto.AccountDetailsDto;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {AccountDetailsDto.class})
public class PropertiesConfig {
    
}
