package com.concordioncode.employee;



import com.concordioncode.employee.dto.CreateEmployee;
import com.concordioncode.employee.dto.Employee;
import com.concordioncode.employee.dto.UpdateEmployeeLineManagerRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployeeContext {

    public CreateEmployee createEmployee;
    public Employee employee;
    public UpdateEmployeeLineManagerRequest updateEmployeeLineManagerRequest;
}
