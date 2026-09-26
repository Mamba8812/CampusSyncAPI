package com.example.campussync.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.campussync.model.Materia;
import com.example.campussync.repository.MateriaRepository;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    //creamso un contrcutor para conectar el repositorio de materias con el controlador.
    // Esto permite que Spring inyecte automáticamente el repositorio cuando se crea el controlador.
private final MateriaRepository materiaRepository;

public MateriaController(MateriaRepository materiaRepository) {
    this.materiaRepository = materiaRepository;
}

    // --- 1. ENDPOINT PARA CREAR UNA MATERIA (POST) ---
    @PostMapping
    public Materia crearMateria(@RequestBody Materia nuevaMateria) {
        try {
            return materiaRepository.save(nuevaMateria);
        } catch (Exception e) {
            System.out.println("Error al crear la materia: " + e.getMessage());
            return null;
        }
    }

    // --- 2. ENDPOINT PARA VER TODAS LAS MATERIAS (GET) ---
    @GetMapping
    public List<Materia> obtenerMaterias() {
        try {
            return materiaRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener las materias: " + e.getMessage());
            return null;
        }
    }

    // --- 3. ENDPOINT PARA ACTUALIZAR UNA MATERIA (PUT) ---
    @PutMapping("/{id}")
    public Materia actualizarMateria(@PathVariable Long id, @RequestBody Materia detallesMateria) {
        try {
            if (materiaRepository.existsById(id)) {
                
                Materia materiaExistente = materiaRepository.findById(id).get();

                // Actualizamos los campos
                materiaExistente.setNombreMateria(detallesMateria.getNombreMateria());
                materiaExistente.setNombreProfesor(detallesMateria.getNombreProfesor());
                materiaExistente.setFechaInicio(detallesMateria.getFechaInicio());
                materiaExistente.setFechaFinal(detallesMateria.getFechaFinal());
                
                // Actualizamos a qué estudiante le pertenece
                materiaExistente.setUsuario(detallesMateria.getUsuario());

                return materiaRepository.save(materiaExistente);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la materia: " + e.getMessage());
            return null;
        }
    }

    // --- 4. ENDPOINT PARA ELIMINAR UNA MATERIA (DELETE) ---
    @DeleteMapping("/{id}")
    public String eliminarMateria(@PathVariable Long id) {
        try {
            if (materiaRepository.existsById(id)) {
                materiaRepository.deleteById(id);
                return "Materia eliminada correctamente";
            } else {
                return "La materia no existe en la base de datos";
            }
        } catch (Exception e) {
            return "Ocurrió un error al intentar eliminar: " + e.getMessage();
        }
    }
}