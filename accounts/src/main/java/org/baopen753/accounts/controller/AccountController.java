package org.baopen753.accounts.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.baopen753.accounts.constant.AccountConstant;
import org.baopen753.accounts.dto.AccountDetailsDto;
import org.baopen753.accounts.dto.CustomerDto;
import org.baopen753.accounts.dto.ResponseDto;
import org.baopen753.accounts.service.IAccountService;
import org.baopen753.accounts.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated              // tells spring boot to perform validation on all REST APIs
public class AccountController {


    private final IAccountService accountService;
    private final ICustomerService customerService;

    @Autowired
    private AccountDetailsDto accountDetailsDto;

    public AccountController(IAccountService accountService, ICustomerService customerService, AccountDetailsDto accountDetailsDto) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.accountDetailsDto = accountDetailsDto;
    }


    @PostMapping("/account")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }


    @GetMapping("/customer")
    public ResponseEntity<CustomerDto> getCustomerInfo(@RequestParam String email) {
        CustomerDto customerDto = customerService.getAccountInfo(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }


    @PutMapping("/customer")
    public ResponseEntity<CustomerDto> updateCustomerInfo(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = accountService.updateAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCustomer);
    }


    @DeleteMapping("/customer")
    public ResponseEntity<ResponseDto> deleteCustomerInfo(@RequestParam
                                                          @Pattern(regexp = "(^$[0-9]{10})", message = "Mobile number should be 10 digits")
                                                          String mobileNumber) {
        accountService.deleteAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }

    @GetMapping("/account-info")
    public ResponseEntity<AccountDetailsDto> getAccountInfo(){
        return ResponseEntity.ok(accountDetailsDto);
    }

}
