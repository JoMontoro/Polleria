package com.example.integrador.Config;

import com.example.integrador.Services.UsuarioServicio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                // Rutas públicas
                .requestMatchers(
                    "/",
                    "/index",
                    "/js/**", 
                    "/css/**", 
                    "/img/**",
                    "/styles/**",
                    "/fragments/**",
                    "/error",
                    "/contacto",
                    "/menu",
                    "/carta",
                    "/carrito",
                    "/login",
                    "/registro"
                ).permitAll()
                // Rutas protegidas que requieren autenticación
                .requestMatchers(
                    "/listageneral/**",
                    "/clientelista/**",
                    "/empleadoslista/**",
                    "/nuevoProducto/**",
                    "/cheflista/**",
                    "/formclientes/**",
                    "/formempleados/**",
                    "/formchef/**",
                    "/registrarclientes/**",
                    "/registrarempleados/**",
                    "/registrarchef/**",
                    "/getEdit/**",
                    "/getEditempleados/**",
                    "/getEditchefs/**",
                    "/delete/**",
                    "/deleteempleados/**",
                    "/deletechefs/**",
                    "/excelx/**",
                    "/excele/**",
                    "/excelc/**"
                ).authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/listageneral", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );

        return http.build();
    }
}