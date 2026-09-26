package com.example.campussync.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity  //permite a Spring convertir la clase en una entidad/tabla
@Table(name = "usuarios") //nombre de la entidad/tabla
public class Usuario {

    @Id // 3. indicamos que este campo es la Llave Primaria (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. Hace que el ID sea autoincrementable (1, 2, 3...)
    
    //creamos objetos/atributos de la clase Usuario, que serán las columnas de la tabla usuarios
    private Long idUsuario;

    private String nombreCompleto;
    private String correo;
    private String semestre;

    // creamos constructor vacío (sin parámetros) para poder crear objetos de la clase Usuario
    public Usuario() {
    }

    // creamos getters y setters para poder acceder y modificar los atributos de la clase Usuario
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}