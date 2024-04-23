package com.mycompany.interviewtask.mapper;

import com.mycompany.interviewtask.domain.CustomerDomain;
import com.mycompany.interviewtask.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    CustomerDomain toDomain(Customer customer);

}
