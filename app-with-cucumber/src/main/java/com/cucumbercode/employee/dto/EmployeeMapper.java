package com.cucumbercode.employee.dto;


import com.cucumbercode.employee.db.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    EmployeeEntity toEntity(CreateEmployee createEmployee);


    Employee fromEntity(EmployeeEntity entity);
}
