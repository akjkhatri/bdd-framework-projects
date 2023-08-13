package com.cucumbercode.employee;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RequiredArgsConstructor
public class EmployeeSteps {

    private final EmployeeContext employeeContext;
    private final EmployeeHelper helper;


    @Given("the HR has onboarded {} {} as a new employee with {} {} monthly stipend")
    public void theHrHasAllRequiredDataForEmployeeOnboarding(String firstName, String lastName, BigDecimal salaryAmount, String currency) {
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

    @Given("the HR already onboarded {} {} as an employee in the system")
    public void theHRAlreadyHaveAnEmployeeInTheSystem(String firstName, String lastName) {
        employeeContext.employee = helper.callEmployeeCreate(helper.buildCreateEmployeeRequest(firstName, lastName, BigDecimal.valueOf(1500), "GBP"));
    }

    @SneakyThrows
    @When("HR processed request for account deactivation")
    public void theHRProcessedRequestForAccountDeactivation() {
        helper.callEmployeeDeactivate();
    }

    @SneakyThrows
    @Then("the employee account status should be {}")
    public void theEmployeeAccountShouldStatusShouldBe(String status) {
        employeeContext.employee = helper.callEmployeeFetch();
        assertFalse(employeeContext.employee.active());
    }

    @When("HR processed request for update line manager for employee to {}")
    public void theHRUpdateEmployeeLineManager(String newManager) {
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
