package com.example.demopetvet.entity;

public class Testimonio {
    private String nombre;
    private String experiencia;

    public Testimonio(String nombre, String experiencia) {
        this.nombre = nombre;
        this.experiencia = experiencia;
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}