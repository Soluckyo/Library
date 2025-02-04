package org.lib.library.service;

import lombok.AllArgsConstructor;
import org.lib.library.entity.Employee;
import org.lib.library.entity.Role;
import org.lib.library.repository.EmployeeRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    public void registerEmployee(Employee employee) throws Exception {
        if(employeeRepo.existsByEmail(employee.getEmail())){
            throw new Exception("Пользователь с таким Email уже существует");
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepo.save(employee);
    }

    public void assignoleAndLibrary(Long employeeId, Role role, Long libraryId){
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
        employee.setRole(role);
        
    }




}
