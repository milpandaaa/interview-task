package com.mycompany.interviewtask.impl;

import com.mycompany.interviewtask.domain.CustomerDomain;

import java.util.List;

public interface CustomerService {

    List<CustomerDomain> saveToFileNameByPath(String toPath, String fromPath);
}
