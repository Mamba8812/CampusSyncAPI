package com.example.campussync.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;

    private String titulo;
    private String descripcion;
    
    // Usamos LocalDate para llevar el control exacto de cuándo se entrega
    private LocalDate fechaVencimiento;
    // Guardará textos como "Pendiente" o "Entregado"
    private String estado;
    // Aquí conectamos la tarea con su "carpeta" (la materia a la que pertenece)

    @ManyToOne//indicacion de cardenalidad (muchas tareas pueden pertenecer a una sola materia)
    @JoinColumn(name = "id_materia")
    private Materia materia;

    // Constructor vacío
    public Tarea() {
    }

    // --- GETTERS Y SETTERS ---

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}