package org.baopen753.accounts.service;

import org.baopen753.accounts.dto.CustomerDto;

public interface ICustomerService {
    CustomerDto getAccountInfo(String email);
}
