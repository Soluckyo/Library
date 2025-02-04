package org.lib.library.api;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/registration/")
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEmployeeController {
    private final EmployService employService;
}
