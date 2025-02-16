package org.lib.library.api;


import lombok.AllArgsConstructor;
import org.lib.library.entity.Employee;
import org.lib.library.entity.Library;
import org.lib.library.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/admin/")
@AllArgsConstructor
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) {

        if(employeeService.existByEmail(employee.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Работник с таким email уже зарегистрирован");
        } else {
            employeeService.registerEmployee(employee);
            return ResponseEntity.status(HttpStatus.OK).body("Работник успешно зарегистрирован");
        }
    }

    @DeleteMapping("/delete_employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Работник успешно удален");
    }


    @PostMapping("/add_library")
    public ResponseEntity<String> addLibrary(@RequestBody Library library) {

        if(employeeService.existByName(library.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Такая библиотека уже существует");
        }else {
            employeeService.addLibrary(library);
            return ResponseEntity.status(HttpStatus.OK).body("Новая библиотека успешно добавлена");
        }
    }

    @DeleteMapping("/delete_library/{idLibrary}")
    public ResponseEntity<String> deleteLibrary(@PathVariable Long idLibrary) {

        employeeService.deleteLibrary(idLibrary);
        return ResponseEntity.status(HttpStatus.OK).body("Библиотека успешно удалена");

    }

    //TODO:approve employee



}

