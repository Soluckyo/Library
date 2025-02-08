package org.lib.library.api;


import lombok.AllArgsConstructor;

import org.lib.library.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/registration/")
@AllArgsConstructor
public class RegistrationEmployeeController {
    private EmployeeService employeeService;
        //TODO:
}

