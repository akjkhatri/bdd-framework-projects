package com.cucumbercode.employee;

import com.cucumbercode.employee.db.EmployeeRepository;
import com.cucumbercode.employee.dto.CreateEmployee;
import com.cucumbercode.employee.dto.Employee;
import com.cucumbercode.employee.dto.EmployeeMapper;
import com.cucumbercode.employee.dto.UpdateEmployeeLineManagerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeAction {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public Employee createEmployee(CreateEmployee createEmployee) {
        var entity = employeeMapper.toEntity(createEmployee);
        var saved = employeeRepository.save(entity);
        return employeeMapper.fromEntity(saved);
    }

    @Transactional
    public void deactivateEmployee(long id) {
        var updated = employeeRepository.updateEmployeeStatus(id, false);
        if (updated == 1) {
            log.info("Employee of ID %s updated successfully".formatted(id));
        }
    }

    public Employee fetchEmployeeById(long id) {
        var entity = employeeRepository.findById(id).orElseThrow();
        return employeeMapper.fromEntity(entity);
    }

    @Transactional
    public void updateEmployeeLineManager(UpdateEmployeeLineManagerRequest request) {
        var updated = employeeRepository.updateEmployeeManager(request.id(), request.oldManager(), request.newManager());
        if (updated == 1) {
            log.info("Employee of ID %s updated successfully".formatted(request.id()));
        }
    }
}
