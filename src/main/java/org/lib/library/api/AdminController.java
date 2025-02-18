package org.lib.library.api;


import lombok.AllArgsConstructor;
import org.lib.library.entity.Employee;
import org.lib.library.entity.Library;
import org.lib.library.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/admin/")
@AllArgsConstructor
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) {
        try {
            if (employeeService.existByEmail(employee.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Работник с таким email уже зарегистрирован");
            } else {
                employeeService.registerEmployee(employee);
                return ResponseEntity.status(HttpStatus.OK).body("Регистрация прошла успешно, ожидайте подтверждения");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка регистрации" + e.getMessage());
        }
    }

    @GetMapping("/pending_employees")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> pendingEmployees() {
        try {
            List<Employee> pendingEmployees = employeeService.getPendingEmployees();
            if(pendingEmployees.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Список пуст");
            }
            return ResponseEntity.status(HttpStatus.OK).body(pendingEmployees.toString());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    //TODO:approve employee
    @GetMapping("/approve/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> approveEmployee(@PathVariable Long id) {
        try {
            employeeService.approveEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).body("Регистрация сотрудника подтверждена");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сотрудник не найден");
        }
    }

    @PostMapping("/reject/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> rejectEmployee(@PathVariable Long id) {
        try{
            employeeService.rejectEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).body("Регистрация сотрудника успешно отклонена");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не удалось отклонить регистрацию" + e.getMessage());
        }
    }

    @DeleteMapping("/delete_employee/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Работник успешно удален");
    }


    @PostMapping("/add_library")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addLibrary(@RequestBody Library library) {

        if(employeeService.existByName(library.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Такая библиотека уже существует");
        }else {
            employeeService.addLibrary(library);
            return ResponseEntity.status(HttpStatus.OK).body("Новая библиотека успешно добавлена");
        }
    }

    @DeleteMapping("/delete_library/{idLibrary}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteLibrary(@PathVariable Long idLibrary) {

        employeeService.deleteLibrary(idLibrary);
        return ResponseEntity.status(HttpStatus.OK).body("Библиотека успешно удалена");
    }


}

