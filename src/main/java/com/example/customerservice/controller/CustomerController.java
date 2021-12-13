package com.example.customerservice.controller;

import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{customerId}")
    public CustomerDto getCustomer(@PathVariable String customerId){
        return customerService.findByCustomerId(customerId);
    }
}
