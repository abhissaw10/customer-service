package com.example.customerservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Customer {

    public Customer(String firstName, String lastName) {
        Assert.isTrue(firstName != null && !"".equalsIgnoreCase(firstName) , "First Name cannot be empty");
        this.firstName=firstName;
        this.lastName=lastName;
    }
    @Id
    @GeneratedValue(generator = "CustomerGenerator")
    @GenericGenerator(
            name = "CustomerGenerator",
            strategy = "com.example.customerservice.repository.CustomerIdGenerator"
    )
    private String customerId;
    private String firstName;
    private String lastName;
}
