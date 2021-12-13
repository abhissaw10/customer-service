package com.example.customerservice.component;

import com.example.customerservice.dto.CustomerDto;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(properties = {"spring.sql.init.mode=always",
        "spring.jpa.show-sql=true","spring.flyway.enabled=true"
},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CustomerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    public static String CUSTOMER_ID = "CUS1234567";

    @Container
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",()->container.getJdbcUrl());
        registry.add("spring.datasource.username",()->container.getUsername());
        registry.add("spring.datasource.password",()->container.getPassword());
        registry.add("spring.jpa.database-platform", PostgreSQL9Dialect.class::getName);
    }

    @Sql(statements = {"insert into customer(customer_id, first_name, last_name) values ('CUS1234567','Abhishek','Sawhney');"})
    @Test
    void returnCustomerInfo(){
        CustomerDto customerDto = restTemplate.getForObject("/customer/{customerId}", CustomerDto.class,CUSTOMER_ID);
        Assertions.assertEquals(CUSTOMER_ID,customerDto.getCustomerId());
    }
}
