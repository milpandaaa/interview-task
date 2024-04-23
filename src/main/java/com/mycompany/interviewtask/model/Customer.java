package com.mycompany.interviewtask.model;

import lombok.Data;

@Data
public class Customer {
    private String firstName;
    private String lastName;
    private String status;
    private Integer numberOfPurchases;
    private Integer numberOfReturns;
    private String phoneNumber;
    private Integer rating;

}
