package org.lib.library.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class EmployeesDetails implements UserDetails {

    private final Employee employee;

     public EmployeesDetails(Employee employee) {
         this.employee = employee;
     }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(Role.values())// cannot find symbol:   method getRole()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return employee.getPassword(); //cannot find symbol:   method getPassword()
    }

    @Override
    public String getUsername() {
        return employee.getEmail(); // cannot find symbol:   method getEmail()
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
