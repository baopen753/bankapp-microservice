package org.baopen753.loans.config;

import org.baopen753.loans.dto.LoanDetailDto;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = LoanDetailDto.class)
public class PropertiesConfig {

}
