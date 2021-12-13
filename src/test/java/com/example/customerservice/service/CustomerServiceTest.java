package com.example.customerservice.service;

import com.example.customerservice.domain.Customer;
import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService cut;

    @Mock
    CustomerRepository customerRepository;

    public static String CUSTOMER_ID = "CUSTOMER12345";

    @Test
    void shouldReturnCustomerBasedonCustomerId(){
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(new Customer("Abhishek","Sawhney")));
        CustomerDto customer = cut.findByCustomerId(CUSTOMER_ID);
        verify(customerRepository, times(1)).findById(anyString());
    }

    @Test
    void shouldReturnErrorIfCustomerIdDoesNotExist(){
        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class).isThrownBy(()->cut.findByCustomerId(CUSTOMER_ID));
    }
}
