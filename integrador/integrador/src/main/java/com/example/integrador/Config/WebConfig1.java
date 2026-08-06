package com.example.integrador.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class WebConfig1 implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mantener la configuración por defecto para recursos estáticos
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        // Agregar configuración específica para uploads
        Path uploadDir = Paths.get(uploadPath).toAbsolutePath().normalize();
        registry.addResourceHandler("/img/uploads/**")
                .addResourceLocations(uploadDir.toUri().toString());
    }
}
