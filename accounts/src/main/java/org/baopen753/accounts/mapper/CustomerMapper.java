package org.baopen753.accounts.mapper;

import org.baopen753.accounts.dto.CustomerDto;
import org.baopen753.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto maptoCustomerDto(CustomerDto customerDto, Customer customer) {
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer maptoCustomer(CustomerDto customerDto, Customer customer) {
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}
