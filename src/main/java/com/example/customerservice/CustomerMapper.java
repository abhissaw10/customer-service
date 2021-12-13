package com.example.customerservice;

import com.example.customerservice.domain.Customer;
import com.example.customerservice.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface CustomerMapper {

    public CustomerDto toDto(Customer customer);

}
