package com.jbehavecode.employee.dto;

import lombok.Builder;

@Builder
public record UpdateEmployeeLineManagerRequest (
        long id,
        String oldManager,
        String newManager
) {
}
