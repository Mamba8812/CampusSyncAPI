package com.example.campussync.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "apuntes")
public class Apunte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApunte;

    private String titulo;
    
    // Con TEXT le decimos a la base de datos que este campo guardará párrafos largos, no solo un renglón
    @Column(columnDefinition = "TEXT")
    private String contenido;

    private LocalDate fechaCreacion;

    // Conectamos el apunte con la materia a la que pertenece
    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;

    // Constructor vacío
    public Apunte() {
    }

    // --- GETTERS Y SETTERS ---

    public Long getIdApunte() {
        return idApunte;
    }

    public void setIdApunte(Long idApunte) {
        this.idApunte = idApunte;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}