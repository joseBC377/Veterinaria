package com.example.demopetvet.utils;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demopetvet.entity.Cliente;

public class CustomUser implements UserDetails{
    
    public Cliente cliente;
 
    public CustomUser(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(cliente.getRol());
        return Arrays.asList(authority);
    }  
    @Override
    public String getPassword() {
        return cliente.getContrasena();
    }

    @Override
    public String getUsername() {
        return cliente.getEmail();
    }    
}
