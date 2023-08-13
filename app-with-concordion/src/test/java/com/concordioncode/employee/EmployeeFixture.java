package com.concordioncode.employee;

import org.chiknrice.test.SpringifiedConcordionRunner;
import org.concordion.api.FullOGNL;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringifiedConcordionRunner.class)
@ContextConfiguration
@FullOGNL
@SpringBootTest
public class EmployeeFixture {

    @Autowired
    private EmployeeContext employeeContext;

    @Autowired
    private EmployeeHelper helper;

    public void theHrHasAllRequiredDataForEmployeeOnboarding(String firstName, String lastName, BigDecimal salaryAmount, String currency) {
        employeeContext.createEmployee = helper.buildCreateEmployeeRequest(firstName, lastName, salaryAmount, currency);
    }

    public void theHrCreateANewRecordInSystem() {
        employeeContext.employee = helper.callEmployeeCreate(employeeContext.createEmployee);
    }

    public void theSystemShouldReturnAllExpectedEmployeeDetails() {
        Assertions.assertNotNull(employeeContext.employee);
        Assertions.assertEquals(employeeContext.employee.firstName(), employeeContext.createEmployee.firstName());
        Assertions.assertEquals(employeeContext.employee.lastName(), employeeContext.createEmployee.lastName());
        Assertions.assertEquals(employeeContext.employee.salaryAmount(), employeeContext.createEmployee.salaryAmount());
        Assertions.assertEquals(employeeContext.employee.job(), employeeContext.createEmployee.job());
        Assertions.assertEquals(employeeContext.employee.manager(), employeeContext.createEmployee.manager());
        Assertions.assertTrue(employeeContext.employee.active());
    }

    public void theHRAlreadyHaveAnEmployeeInTheSystem(String firstName, String lastName) {
        employeeContext.employee = helper.callEmployeeCreate(helper.buildCreateEmployeeRequest(firstName, lastName, BigDecimal.valueOf(1500), "GBP"));
    }

    public void theHRProcessedRequestForAccountDeactivation() {
        helper.callEmployeeDeactivate();
    }

    public void theEmployeeAccountShouldStatusShouldBe(String status) {
        employeeContext.employee = helper.callEmployeeFetch();
        assertFalse(employeeContext.employee.active());
    }

    public void theHRUpdateEmployeeLineManager(String newManager) {
        employeeContext.updateEmployeeLineManagerRequest = helper.buildUpdateEmployeeLineManagerRequest(employeeContext.employee.id(), employeeContext.employee.manager(), newManager);
        helper.callUpdateEmployeeManager(employeeContext.updateEmployeeLineManagerRequest);
    }

    public void theEmployeeShouldBeInNewManagerHierarchy() {
        employeeContext.employee = helper.callEmployeeFetch();
        assertEquals(employeeContext.updateEmployeeLineManagerRequest.newManager(), employeeContext.employee.manager());
        assertNotEquals(employeeContext.updateEmployeeLineManagerRequest.oldManager(), employeeContext.employee.manager());
    }
}
