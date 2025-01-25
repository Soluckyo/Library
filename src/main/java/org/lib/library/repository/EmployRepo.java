package org.lib.library.repository;

import org.lib.library.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployRepo extends JpaRepository<Employee, Long> {
}
