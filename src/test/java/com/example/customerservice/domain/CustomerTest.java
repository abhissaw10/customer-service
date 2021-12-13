package com.example.customerservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    void shouldReturnErrorIfCustomerFirstNameIsNull(){
        Assertions.assertThatIllegalArgumentException().isThrownBy(()->new Customer(null, "Sawhney"));
    }
}
