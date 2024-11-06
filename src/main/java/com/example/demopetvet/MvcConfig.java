package com.example.demopetvet;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/reservas").setViewName("reservas");
        // registry.addViewController("/contactanos").setViewName("contactanos");
        registry.addViewController("/servicios").setViewName("servicios");
        registry.addViewController("/productos").setViewName("productos");   
        registry.addViewController("/productos_gatos").setViewName("productos_gatos");
        registry.addViewController("/productos_perros").setViewName("productos_perros");   
        registry.addViewController("/producto_otras_mascotas").setViewName("producto_otras_mascotas");   
    }

}
