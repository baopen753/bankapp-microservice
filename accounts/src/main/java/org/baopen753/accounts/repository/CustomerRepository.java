package org.baopen753.accounts.repository;

import org.baopen753.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerByMobileNumber(String mobileNumber);

}
