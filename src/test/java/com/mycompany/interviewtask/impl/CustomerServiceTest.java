package com.mycompany.interviewtask.impl;

import com.mycompany.interviewtask.domain.CustomerDomain;
import com.mycompany.interviewtask.mapper.CustomerMapperImpl;
import com.mycompany.interviewtask.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {


    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        this.customerService = new SaveCustomersImpl(customerRepository, new CustomerMapperImpl());
    }

    @Test
    void saveToFileNameByPath() {
        CustomerDomain customerDomain1 = new CustomerDomain();
        customerDomain1.setRating(590);
        customerDomain1.setFirstName("John");
        customerDomain1.setLastName("Bin");
        customerDomain1.setPhoneNumber("+4-203-332-27-77");

        CustomerDomain customerDomain2 = new CustomerDomain();
        customerDomain2.setRating(150);
        customerDomain2.setFirstName("Galina");
        customerDomain2.setLastName("Petrovovna");
        customerDomain2.setPhoneNumber("+7-111-222-33-44");

        given(customerRepository.save(customerDomain1))
                .willReturn(customerDomain1);

        given(customerRepository.save(customerDomain2))
                .willReturn(customerDomain2);

        List<CustomerDomain> response = customerService.saveToFileNameByPath("src/test/resources/phone_numbers.txt",
                                                                             "src/test/resources/customers.json"
                                                                            );

        Assertions.assertEquals(response.size(), 2);

        StringBuilder phoneNumber = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/phone_numbers.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                phoneNumber.append(line);
            }
            reader.close();

        } catch (Throwable e) {
            throw new RuntimeException("File not found");
        }

        Assertions.assertEquals(phoneNumber.toString(), "+4-203-332-27-77");

    }
}