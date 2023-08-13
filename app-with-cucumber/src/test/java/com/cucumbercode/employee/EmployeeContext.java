package com.cucumbercode.employee;


import com.cucumbercode.employee.dto.CreateEmployee;
import com.cucumbercode.employee.dto.Employee;
import com.cucumbercode.employee.dto.UpdateEmployeeLineManagerRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "cucumber-glue")
public class EmployeeContext {

    public CreateEmployee createEmployee;
    public Employee employee;
    public UpdateEmployeeLineManagerRequest updateEmployeeLineManagerRequest;
}
