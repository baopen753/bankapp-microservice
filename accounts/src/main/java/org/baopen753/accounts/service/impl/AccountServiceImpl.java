package org.baopen753.accounts.service.impl;

import lombok.AllArgsConstructor;
import org.baopen753.accounts.constant.AccountConstant;
import org.baopen753.accounts.dto.AccountDto;
import org.baopen753.accounts.dto.CustomerDto;
import org.baopen753.accounts.entity.Account;
import org.baopen753.accounts.entity.Customer;
import org.baopen753.accounts.exception.CustomerAlreadyExistException;
import org.baopen753.accounts.exception.ObjectNotFoundException;
import org.baopen753.accounts.mapper.AccountMapper;
import org.baopen753.accounts.mapper.CustomerMapper;
import org.baopen753.accounts.repository.AccountRepository;
import org.baopen753.accounts.repository.CustomerRepository;
import org.baopen753.accounts.service.IAccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param customerDto {@link CustomerDto}
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.maptoCustomer(customerDto, new Customer());

        Optional<Customer> customerInDb = customerRepository.findCustomerByEmail(customerDto.getEmail());
        if (customerInDb.isPresent())
            throw new CustomerAlreadyExistException("Customer is already registered with given email: " + customerDto.getEmail());

        customer.setCreatedBy(customerDto.getName());
        customer.setCreatedAt(LocalDateTime.now());

        Customer savedCustomer = customerRepository.save(customer);    // spring data jpa do: take the input object, prepare SQL statement, create a connection with DB, execute the statement, commit transaction, close connection

        Account account = setAccountFromCustomerInfo(savedCustomer);
        accountRepository.save(account);
    }

    @Override
    public CustomerDto updateAccount(CustomerDto customerDto) {

        CustomerDto updatedCustomerDto = new CustomerDto();
        AccountDto accountDto = customerDto.getAccountDto();

        if (accountDto != null) {
            Account accountInDb = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ObjectNotFoundException("Account", "account_number", accountDto.getAccountNumber())
            );

            AccountMapper.mapToAccount(accountDto, accountInDb);
            accountRepository.save(accountInDb);

            Customer customerInDb = customerRepository.findById(accountInDb.getCustomerId()).orElseThrow(
                    () -> new ObjectNotFoundException("Customer", "customer_id", accountInDb.getCustomerId())
            );

            CustomerMapper.maptoCustomer(customerDto, customerInDb);
            customerRepository.save(customerInDb);
            updatedCustomerDto = CustomerMapper.maptoCustomerDto(customerDto, customerInDb);
            updatedCustomerDto.setAccountDto(accountDto);
        }
        return updatedCustomerDto;
    }

    @Override
    public void deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber).orElseThrow(
                () -> new ObjectNotFoundException("Customer", "mobile_number", mobileNumber)
        );

        Account accountInDb = accountRepository.findAccountByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ObjectNotFoundException("Account", "customer_id", customer.getCustomerId())
        );

        accountRepository.delete(accountInDb);
    }


    private Account setAccountFromCustomerInfo(Customer customer) {
        Account newAccount = new Account();
        Long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstant.SAVINGS);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);

        newAccount.setCreatedBy(customer.getName());
        newAccount.setCreatedAt(LocalDateTime.now());

        return newAccount;
    }
}