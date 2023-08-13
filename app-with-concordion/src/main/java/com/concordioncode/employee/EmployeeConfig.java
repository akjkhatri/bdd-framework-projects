package com.concordioncode.employee;

import com.concordioncode.employee.dto.EmployeeMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    @Bean
    public EmployeeMapper getEmployeeMapper() {
        return Mappers.getMapper(EmployeeMapper.class);
    }
}
