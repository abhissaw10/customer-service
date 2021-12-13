package com.example.customerservice.controller;


import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;
    public static String CUSTOMER_ID = "CUST1234567";

    ObjectMapper mapper = null;

    @MockBean
    private CustomerService customerService;

    @Test
    void shouldGetCustomerBasedOnCustomerId() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().customerId(CUSTOMER_ID).firstName("Abhishek").lastName( "Sawhney").build();
        when(customerService.findByCustomerId(anyString())).thenReturn(customerDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/{customerId}",CUSTOMER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(".customerId").value(CUSTOMER_ID));
        verify(customerService,times(1)).findByCustomerId(anyString());
    }
}
