package org.lib.library.service;

import org.lib.library.entity.Employee;
import org.lib.library.entity.EmployeesDetails;
import org.lib.library.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepo.findByEmail(email);
        return employee.map(EmployeesDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
