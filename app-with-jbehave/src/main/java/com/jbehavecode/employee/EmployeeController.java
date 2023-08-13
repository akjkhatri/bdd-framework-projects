package com.jbehavecode.employee;



import com.jbehavecode.employee.dto.CreateEmployee;
import com.jbehavecode.employee.dto.Employee;
import com.jbehavecode.employee.dto.UpdateEmployeeLineManagerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeAction employeeAction;

    @PostMapping(value = "/employee/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createNewEmployee(@RequestBody final CreateEmployee createEmployee) {
        return ResponseEntity.ok(employeeAction.createEmployee(createEmployee));
    }

    @PostMapping(value = "/employee/deactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deactivateEmployee(@PathVariable long id) {
        employeeAction.deactivateEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> fetchEmployee(@PathVariable long id) {
        return ResponseEntity.ok(employeeAction.fetchEmployeeById(id));
    }

    @PostMapping(value = "/employee/update/linemanager", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEmployeeLineManager(@RequestBody final UpdateEmployeeLineManagerRequest request) {
        employeeAction.updateEmployeeLineManager(request);
        return ResponseEntity.ok().build();
    }
}
