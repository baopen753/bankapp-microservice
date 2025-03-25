package org.baopen753.accounts.repository;

import org.baopen753.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByCustomerId(Long customerId);
}
