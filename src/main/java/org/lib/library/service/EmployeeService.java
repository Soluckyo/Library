package org.lib.library.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.lib.library.entity.Employee;
import org.lib.library.entity.Guest;
import org.lib.library.entity.Library;
import org.lib.library.repository.EmployeeRepo;
import org.lib.library.repository.GuestRepo;
import org.lib.library.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

    //TODO:approve employee registration

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public void registerEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRegistered(false);
        employeeRepo.save(employee);
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
