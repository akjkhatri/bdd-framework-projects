package com.jbehavecode.employee.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Builder
public record CreateEmployee(
        String firstName,
        String lastName,
        String email,
        String phone,
        Instant joiningDate,
        BigDecimal salaryAmount,
        Currency salaryCurrency,
        String job,
        String department,
        String manager
) {
}
