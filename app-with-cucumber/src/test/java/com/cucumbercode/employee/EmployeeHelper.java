package com.cucumbercode.employee;

import com.cucumbercode.employee.dto.CreateEmployee;
import com.cucumbercode.employee.dto.Employee;
import com.cucumbercode.employee.dto.UpdateEmployeeLineManagerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@Component
@Scope(scopeName = "cucumber-glue")
public class EmployeeHelper {

    private final WebApplicationContext webApplicationContext;
    private final EmployeeContext employeeContext;
    private final ObjectMapper mapper;

    public CreateEmployee buildCreateEmployeeRequest(String firstName, String lastName, BigDecimal salaryAmount, String currency) {
        return CreateEmployee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email("dummy@dummy.com")
                .phone("+449999444400")
                .salaryAmount(salaryAmount)
                .salaryCurrency(Currency.getInstance(currency))
                .joiningDate(Instant.now())
                .department("dummy")
                .job("Developer")
                .manager("Jack")
                .build();
    }

    public UpdateEmployeeLineManagerRequest buildUpdateEmployeeLineManagerRequest(long id, String oldManager, String newManager) {
        return UpdateEmployeeLineManagerRequest.builder()
                .id(id)
                .oldManager(oldManager)
                .newManager(newManager)
                .build();
    }

    @SneakyThrows
    public Employee callEmployeeCreate(CreateEmployee request){
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        var mvcResult = mockMvc.perform(post("/employee/create").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn();
        var response = mvcResult.getResponse().getContentAsString();
        return mapper.readValue(response, Employee.class);
    }

    @SneakyThrows
    public void callEmployeeDeactivate() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(post("/employee/deactivate/"+employeeContext.employee.id()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @SneakyThrows
    public Employee callEmployeeFetch() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        var mvcResult = mockMvc.perform(get("/employee/"+employeeContext.employee.id()))
                .andExpect(status().isOk()).andReturn();
        var response = mvcResult.getResponse().getContentAsString();
        return mapper.readValue(response, Employee.class);
    }

    @SneakyThrows
    public void callUpdateEmployeeManager(UpdateEmployeeLineManagerRequest request) {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(post("/employee/update/linemanager").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn();
    }
}
