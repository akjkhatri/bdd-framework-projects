package com.cucumbercode.employee.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity(name = "EmployeeEntity")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private Instant joiningDate;
    @Column
    private BigDecimal salaryAmount;
    @Column
    private Currency salaryCurrency;
    @Column
    private String job;
    @Column
    private String department;
    @Column
    private String manager;
    @Column
    private boolean active;

}
