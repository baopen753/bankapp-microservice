package org.baopen753.accounts.service.impl;

import lombok.AllArgsConstructor;
import org.baopen753.accounts.dto.AccountDto;
import org.baopen753.accounts.dto.CustomerDto;
import org.baopen753.accounts.entity.Account;
import org.baopen753.accounts.entity.Customer;
import org.baopen753.accounts.exception.ObjectNotFoundException;
import org.baopen753.accounts.mapper.AccountMapper;
import org.baopen753.accounts.mapper.CustomerMapper;
import org.baopen753.accounts.repository.AccountRepository;
import org.baopen753.accounts.repository.CustomerRepository;
import org.baopen753.accounts.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    public CustomerDto getAccountInfo(String email) {
        // Fetch customer by email or throw exception if not found
        Customer customer = customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Customer", "email", email));

        // Fetch account by customer ID or throw exception if not found
        Account account = accountRepository.findAccountByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ObjectNotFoundException("Account", "customerId", customer.getCustomerId()));

        // Map customer and account to DTOs
        CustomerDto customerDto = CustomerMapper.maptoCustomerDto(new CustomerDto(), customer);
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        return customerDto;
    }

}
