package com.jbehavecode.employee;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Component
@RequiredArgsConstructor
public class EmployeeSteps {

    private final EmployeeContext employeeContext;
    private final EmployeeHelper helper;

    @Given("the HR has onboarded $firstName $lastName as a new employee with $salaryAmount $currency monthly stipend")
    public void theHrHasAllRequiredDataForEmployeeOnboarding(@Named("firstName") String firstName, @Named("lastName") String lastName, @Named("salaryAmount") BigDecimal salaryAmount, @Named("currency") String currency) {
        employeeContext.createEmployee = helper.buildCreateEmployeeRequest(firstName, lastName, salaryAmount, currency);
    }

    @SneakyThrows
    @When("the HR submit request to create a new record in the system")
    public void theHrCreateANewRecordInSystem() {
        employeeContext.employee = helper.callEmployeeCreate(employeeContext.createEmployee);
    }

    @Then("the system should show the expected employee detail")
    public void theSystemShouldReturnAllExpectedEmployeeDetails() {
        assertNotNull(employeeContext.employee);
        assertEquals(employeeContext.employee.firstName(), employeeContext.createEmployee.firstName());
        assertEquals(employeeContext.employee.lastName(), employeeContext.createEmployee.lastName());
        assertEquals(employeeContext.employee.salaryAmount(), employeeContext.createEmployee.salaryAmount());
        assertEquals(employeeContext.employee.job(), employeeContext.createEmployee.job());
        assertEquals(employeeContext.employee.manager(), employeeContext.createEmployee.manager());
        assertTrue(employeeContext.employee.active());
    }

    @Given("the HR already onboarded $firstName $lastName as an employee in the system")
    public void theHRAlreadyHaveAnEmployeeInTheSystem(@Named("firstName") String firstName, @Named("lastName") String lastName) {
        employeeContext.employee = helper.callEmployeeCreate(helper.buildCreateEmployeeRequest(firstName, lastName, BigDecimal.valueOf(1500), "GBP"));
    }

    @SneakyThrows
    @When("HR processed request for account deactivation")
    public void theHRProcessedRequestForAccountDeactivation() {
        helper.callEmployeeDeactivate();
    }

    @SneakyThrows
    @Then("the employee account status should be $status")
    public void theEmployeeAccountShouldStatusShouldBe(@Named("status") String status) {
        employeeContext.employee = helper.callEmployeeFetch();
        assertFalse(employeeContext.employee.active());
    }

    @When("HR processed request for update line manager for employee to $newManager")
    public void theHRUpdateEmployeeLineManager(@Named("newManager") String newManager) {
        employeeContext.updateEmployeeLineManagerRequest = helper.buildUpdateEmployeeLineManagerRequest(employeeContext.employee.id(), employeeContext.employee.manager(), newManager);
        helper.callUpdateEmployeeManager(employeeContext.updateEmployeeLineManagerRequest);
    }

    @Then("the employee should show under Nancy team hierarchy")
    public void theEmployeeShouldBeInNewManagerHierarchy() {
        employeeContext.employee = helper.callEmployeeFetch();
        assertEquals(employeeContext.updateEmployeeLineManagerRequest.newManager(), employeeContext.employee.manager());
        assertNotEquals(employeeContext.updateEmployeeLineManagerRequest.oldManager(), employeeContext.employee.manager());
    }
}
