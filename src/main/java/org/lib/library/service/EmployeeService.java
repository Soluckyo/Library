package org.lib.library.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.lib.library.entity.Employee;
import org.lib.library.entity.Guest;
import org.lib.library.entity.Library;
import org.lib.library.entity.Role;
import org.lib.library.repository.EmployeeRepo;
import org.lib.library.repository.GuestRepo;
import org.lib.library.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private LibraryRepo libraryRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private GuestRepo guestRepo;

    @Transactional
    public void registerEmployee(Employee employee) throws Exception {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRegistered(false);
        employeeRepo.save(employee);
    }

    @Transactional
    public void approveEmployee(Long id) throws Exception {
        Optional<Employee> employeeOpt = employeeRepo.findById(id);
        if(employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setRole(Role.EMPLOYEE);
            employee.setRegistered(true);

            employeeRepo.save(employee);
        }else throw new Exception("Сотрудник не найден");
    }

    @Transactional
    public void rejectEmployee(Long id) throws Exception {
        Optional<Employee> employeeOpt = employeeRepo.findById(id);
        if(employeeOpt.isPresent()) {
            deleteEmployee(id);
        }
    }

    public List<Employee> getPendingEmployees() {
        return employeeRepo.findByIsRegisteredFalse();
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public boolean existByEmail(String email) {
        return employeeRepo.existsByEmail(email);
    }

    public void addLibrary(Library library) {
        libraryRepo.save(library);
    }

    public boolean existByName(String name) {
        return libraryRepo.existsByName(name);
    }

    public void deleteLibrary(Long id) {
        libraryRepo.deleteById(id);
    }

    public boolean existsByPhone(String phone) {
        return guestRepo.existsByPhone(phone);
    }

    public void registerGuest(Guest guest) {
        guestRepo.save(guest);
    }
}
