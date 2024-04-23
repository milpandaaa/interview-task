package com.mycompany.interviewtask.repository;

import com.mycompany.interviewtask.domain.CustomerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDomain, Long> {

}
