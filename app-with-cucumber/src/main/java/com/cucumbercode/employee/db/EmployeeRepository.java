package com.cucumbercode.employee.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Modifying
    @Query("""
            UPDATE EmployeeEntity entity
            SET entity.active = :active
            WHERE entity.id = :id
        """)
    int updateEmployeeStatus(long id, boolean active);

    @Modifying
    @Query("""
            UPDATE EmployeeEntity entity
            SET entity.manager = :newManager
            WHERE entity.id = :id
            AND entity.manager = :oldManager
        """)
    int updateEmployeeManager(long id, String oldManager, String newManager);
}
