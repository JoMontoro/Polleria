package com.example.integrador.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig1 implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mantener la configuración por defecto para recursos estáticos
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        // Agregar configuración específica para uploads
        registry.addResourceHandler("/img/uploads/**")
                .addResourceLocations("file:src/main/resources/static/img/uploads/");
    }
}