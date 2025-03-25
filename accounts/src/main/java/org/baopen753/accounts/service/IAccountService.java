package org.baopen753.accounts.service;

import org.baopen753.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     * @param customerDto {@link CustomerDto}
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto updateAccount(CustomerDto customerDto);

    void deleteAccount(String mobileNumber);
}
