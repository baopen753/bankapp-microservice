package org.baopen753.accounts.mapper;

import org.baopen753.accounts.dto.AccountDto;
import org.baopen753.accounts.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setAccountNumber(account.getAccountNumber());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto, Account account) {
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setAccountNumber(accountDto.getAccountNumber());
        return account;
    }

}
