package org.lib.library.api;

import org.lib.library.entity.Employee;
import org.lib.library.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/register")
public class RegistrationAdminController {
    EmployeeService employeeService;

    @PostMapping
    public String register(@RequestBody Employee employee) {
        employeeService.registerAdmin(employee);
        return "success";
    }
}
