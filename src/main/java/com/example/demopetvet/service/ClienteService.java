package com.example.demopetvet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    // select* from
    public List<Cliente> selectAll() {
        return repository.findAll();
    }

    // Select *from where
    public Cliente selectById(Integer id) {
        return repository.findById(id).orElse(new Cliente());
    }
    
    public void insUpd(Cliente cliente) {
        // Encriptar contraseña solo si es nueva o ha sido modificada
        if (cliente.getId() == null || cliente.getContrasena() != null && !cliente.getContrasena().isEmpty()) {
            cliente.setContrasena(passwordEncoder.encode(cliente.getContrasena()));
        }
        repository.save(cliente);
    }

    // Delete
    public String delete(Integer id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return String.format("Registro con ID: %d eliminado",id );
        }
        return String.format("ID: %d no encontrado", id);
    }

    public Cliente buscarCorreo(String email){
        return repository.findByEmail(email);
    }


    public void registrarCliente(Cliente cliente) throws Exception {
        // Verificar si el correo ya está registrado
        if (repository.existsByEmail(cliente.getEmail())) {
            throw new Exception("Correo ya registrado");
        }
        
        System.out.println("Registrando cliente: " + cliente.getEmail());  // Log antes del save
        
        // Guardar el cliente en la base de datos
        Cliente registrado = repository.save(cliente);
        
        // Verifica si se ha guardado correctamente
        System.out.println("Cliente registrado correctamente con ID: " + registrado.getId());
    }
    
    
}