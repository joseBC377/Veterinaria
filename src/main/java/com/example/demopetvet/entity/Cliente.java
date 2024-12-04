package com.example.demopetvet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClienteID")
    Integer id;

    @NotBlank(message = "Ingrese un nombre")
    @Pattern(regexp = "\\D*", message = "No puede contener números")
    @Size(min = 2, max = 100, message = "Ingresar un nombre entre 2 y 150 caracteres")
    @Column(name = "Nombre")
    String nombre;

    @NotBlank(message = "Ingrese un apellido")
    @Pattern(regexp = "\\D*", message = "No puede contener números")
    @Size(min = 2, max = 100, message = "Ingresar un apellido entre 2 y 150 caracteres")
    @Column(name = "Apellido")
    String apellido;

    @Email(message = "Ingrese un correo válido")
    @NotBlank(message = "Ingrese un correo")
    @Column(name = "Email", unique = true)
    String email;

    @Size(min = 7, max = 9, message = "El teléfono debe tener un máximo de 9 dígitos")
    @Pattern(regexp = "\\d*", message = "Ingrese solo números")
    @Column(name = "Telefono")
    String telefono;

    @NotBlank(message = "Ingrese una dirección ")
    @Size(min = 2, max = 255, message = "Ingresar una dirección entre 2 y 150 caracteres")
    @Column(name = "Direccion")
    String direccion;

    // Spring security 
    @Column(name = "Contrasena")
    String contrasena;
    
    @Column(name = "rol")
    String rol;
}
