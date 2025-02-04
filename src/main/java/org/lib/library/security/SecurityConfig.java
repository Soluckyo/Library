package org.lib.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((auth) -> auth  // Используем authorizeRequests() для настройки авторизации
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")  // Доступно только для ADMIN
                .requestMatchers("/api/v1/employees/**").hasRole("EMPLOYEE")  // Доступно только для EMPLOYEE
                .requestMatchers("/api/v1/registration").permitAll() // Доступно для всех
                .anyRequest().authenticated()  // Для всех остальных запросов необходима аутентификация
                )
                .formLogin(Customizer.withDefaults())  // Разрешаем доступ к форме логина
                .httpBasic(Customizer.withDefaults());// Поддержка базовой аутентификации (например, через HTTP-заголовок)

        return http.build();  // Возвращаем SecurityFilterChain
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
