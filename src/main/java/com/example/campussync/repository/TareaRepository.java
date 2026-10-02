package com.example.campussync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campussync.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    
    // === MÉTODO DE FILTRADO CORRECTO ===
    // Como tu controlador usa la propiedad "materia", le indicamos a Spring Boot 
    // que busque por "Materia_IdMateria" o simplemente por el campo que vincula ambas tablas.
    // (Spring Boot leerá "FindByMateria" y el ID interno de esa entidad).
    List<Tarea> findByMateriaIdMateria(Long idMateria);
}