package com.example.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDto {

    private String customerId;
    private String firstName;
    private String lastName;

}
