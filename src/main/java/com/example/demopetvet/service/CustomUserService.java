package com.example.demopetvet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.repository.ClienteRepository;
import com.example.demopetvet.utils.CustomUser;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    ClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = repository.findByEmail(username);
        if (cliente == null) {
            System.out.println("Usuario no encontrado: " + username);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        System.out.println("Usuario encontrado: " + cliente.getEmail() + ", Rol: " + cliente.getRol());
        return new CustomUser(cliente);
    }

}
