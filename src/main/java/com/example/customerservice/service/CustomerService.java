package com.example.customerservice.service;

import com.example.customerservice.CustomerMapper;
import com.example.customerservice.domain.Customer;
import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerDto findByCustomerId(String customerId) {
        return mapper.toDto(customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new));
    }

    private CustomerDto entityToDto(Customer customer){
        return CustomerDto
                .builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }



}
