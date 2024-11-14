package com.example.integrador.Config;

import com.example.integrador.Services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
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
                    "/registro/**", // Agregado /** para cubrir todas las subrutas de registro
                    "/registro-exitoso" // Nueva ruta para mensaje de éxito
                ).permitAll()
                // Rutas protegidas que requieren autenticación
                .requestMatchers(
                    "/listageneral/**",
                    "/clientelista/**",
                    "/empleadoslista/**",
                    "/cheflista/**",
                    "/formclientes/**",
                    "/formempleados/**",
                    "/nuevoProducto/**",
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
                    "/excelc/**",
                    "/admin/**" // Agregado para rutas de administrador
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
            )
            .authenticationProvider(authenticationProvider()); // Agregado el provider

        return http.build();
    }
}