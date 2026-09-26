package com.example.campussync.model;

//para indicar a la base de datos que no es texto, sino una fecha calendario real
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity //indica a JPA que esta clase representa una entidad en la base de datos
@Table(name = "materias") //indica a JPA que esta clase se mapea a la tabla "materias" en la base de datos
public class Materia {

    @Id // indica a JPA que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica a JPA que la clave primaria se genera automáticamente en la base de datos
    
    //creamos atributos de la tabla "materias"
    private Long idMateria;
    private String nombreMateria;
    private String nombreProfesor;
    // Usamos LocalDate para manejar fechas reales (AAAA-MM-DD)
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;

    // Aquí está la magia: conectamos esta materia con un estudiante
    @ManyToOne //anotacion que le indica a la base de datos que muchas materias pueden pertenecer a Un solo usuario.
    @JoinColumn(name = "id_usuario")//anotacion que indica la creacion de llave foranea id_usuarios a la tabla "materias"
    private Usuario usuario;//indica a Java que guarde el objeto completo del estudiante

    // Constructor vacío (obligatorio para Spring Boot)
    public Materia() {
    }

    // --- GETTERS Y SETTERS ---

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
