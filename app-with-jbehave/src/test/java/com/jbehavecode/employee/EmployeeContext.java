package com.jbehavecode.employee;


import com.jbehavecode.employee.dto.CreateEmployee;
import com.jbehavecode.employee.dto.Employee;
import com.jbehavecode.employee.dto.UpdateEmployeeLineManagerRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployeeContext {

    public CreateEmployee createEmployee;
    public Employee employee;
    public UpdateEmployeeLineManagerRequest updateEmployeeLineManagerRequest;
}
