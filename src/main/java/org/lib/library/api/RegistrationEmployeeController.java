package org.lib.library.api;


import lombok.AllArgsConstructor;

import org.lib.library.entity.Employee;
import org.lib.library.repository.EmployeeRepo;
import org.lib.library.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/registration/")
@AllArgsConstructor
public class RegistrationEmployeeController {
    private EmployeeService employeeService;
    private EmployeeRepo employeeRepo;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) {
    //TODO:переделать логику на сервис!
        if (employeeRepo == null) {
            throw new IllegalStateException("EmployeeRepo is null");
        }
        if(employeeRepo.findByEmail(employee.getEmail()).isPresent()) {  //cannot find symbol:   method getEmail()
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким email уже зарегистрирован");
        } else {
            employeeService.registerEmployee(employee);
            return ResponseEntity.status(HttpStatus.OK).body("Пользователь успешно зарегистрирован");
        }
    }

    //TODO:approve employee


}

