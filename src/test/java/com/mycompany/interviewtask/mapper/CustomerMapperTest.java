package com.mycompany.interviewtask.mapper;

import com.mycompany.interviewtask.domain.CustomerDomain;
import com.mycompany.interviewtask.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    @Test
    void toDomain() {
        Customer customer = new Customer();
        customer.setFirstName("Ann");
        customer.setLastName("test");
        customer.setNumberOfPurchases(501);
        customer.setPhoneNumber("+4-203-332-27-77");
        customer.setStatus("gold");
        customer.setNumberOfReturns(1);
        CustomerMapper mapper = new CustomerMapperImpl();
        CustomerDomain domain = mapper.toDomain(customer);


        assertEquals(domain.getFirstName(), customer.getFirstName());
        assertEquals(domain.getLastName(), customer.getLastName());
        assertEquals(domain.getRating(), customer.getRating());
        assertEquals(domain.getPhoneNumber(), customer.getPhoneNumber());
    }

}