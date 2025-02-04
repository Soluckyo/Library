package org.lib.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lib.library.entity.Library;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegistrationForm {
    private String name;
    private String email;
    private String password;
    private Library libraryId;
}
