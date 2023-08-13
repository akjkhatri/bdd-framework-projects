package com.cucumbercode.employee.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Builder
public record Employee(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Instant joiningDate,
        BigDecimal salaryAmount,
        Currency salaryCurrency,
        String job,
        String department,
        String manager,
        boolean active
) {
}
