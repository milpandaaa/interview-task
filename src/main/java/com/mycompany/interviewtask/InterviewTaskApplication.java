package com.mycompany.interviewtask;

import com.mycompany.interviewtask.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewTaskApplication implements ApplicationRunner {

    @Autowired
    CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(InterviewTaskApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        customerService.saveToFileNameByPath("src/main/resources/phone_numbers.txt",
                                                               "src/main/resources/customers.json");


    }
}
