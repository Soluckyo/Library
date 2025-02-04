package org.lib.library.repository;

import org.lib.library.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
