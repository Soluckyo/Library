package org.lib.library.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.lib.library.entity.Employee;
import org.lib.library.repository.EmployeeRepo;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeService {

    private EmployeeRepo employeeRepo;

    //TODO:

    public void registerEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public void registerAdmin(Employee employee) {
        employeeRepo.save(employee);
    }









}
