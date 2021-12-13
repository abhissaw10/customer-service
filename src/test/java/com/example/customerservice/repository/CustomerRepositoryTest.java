package com.example.customerservice.repository;

import com.example.customerservice.domain.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CustomerRepository customerRepository;

    public static String customerId = "CUST1234567";

    @Test
    void shouldReturnCustomerInfo(){
        Customer newCustomer = new Customer("Abhishek","Sawhney");
        Customer customer = entityManager.persist(newCustomer);
        Customer findCustomer = customerRepository.findById(customer.getCustomerId()).get();
        Assertions.assertThat(customer.getFirstName()).isEqualTo("Abhishek");
    }
}
