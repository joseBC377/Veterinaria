package com.example.demopetvet;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
//@SuppressWarnings("null")
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //client
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/reservas").setViewName("reservas");
        // registry.addViewController("/contactanos").setViewName("contactanos");
        registry.addViewController("/servicios").setViewName("servicios");
        registry.addViewController("/productos").setViewName("productos");   
        registry.addViewController("/productos_gatos").setViewName("productos_gatos");
        registry.addViewController("/productos_perros").setViewName("productos_perros");   
        registry.addViewController("/producto_otras_mascotas").setViewName("producto_otras_mascotas");  
        registry.addViewController("/perfil").setViewName("perfil"); // CLIENT
        registry.addViewController("/intranet_usuario").setViewName("intranet_usuario");  // ADMIN - // CLIENT
        registry.addViewController("/error403").setViewName("error403");  // ADMIN - // CLIENT
        registry.addViewController("/intranet").setViewName("intranet");  // ADMIN 
        registry.addViewController("/pedido").setViewName("pedido");
        registry.addViewController("/registro").setViewName("registro");  // ADMIN - // CLIENT
        //VISTA ADMIN--ACA ES DONDE SE GESTIONAN LOS CRUD
       registry.addViewController("/categoria_form").setViewName("categoria_form");   //ADMIN
       registry.addViewController("/categoria_form").setViewName("categoria_form");   //ADMIN
       registry.addViewController("/categoria").setViewName("categoria");   //ADMIN
       registry.addViewController("/cliente_form").setViewName("cliente_form");  //ADMIN
       registry.addViewController("/clientes").setViewName("clientes");  //ADMIN
       registry.addViewController("/contacto_form").setViewName("contacto_form");  //ADMIN
       registry.addViewController("/producto_form").setViewName("producto_form");  //ADMIN
       registry.addViewController("/producto").setViewName("producto");  //ADMIN
       registry.addViewController("/reserva_form").setViewName("reserva_form");  //ADMIN
       registry.addViewController("/reserva").setViewName("reserva");  //ADMIN
       registry.addViewController("/servicio_form").setViewName("servicio_form");  //ADMIN
       registry.addViewController("/servicio").setViewName("servicio");  //ADMIN

        registry.addRedirectViewController("/", "/index");
    }
}
