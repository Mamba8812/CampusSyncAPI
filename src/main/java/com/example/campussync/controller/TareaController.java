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

import com.example.campussync.model.Tarea;
import com.example.campussync.repository.TareaRepository;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

private final TareaRepository tareaRepository;

public TareaController(TareaRepository tareaRepository) {
    this.tareaRepository = tareaRepository;
}

    // --- 1. ENDPOINT PARA CREAR UNA TAREA (POST) ---
    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea nuevaTarea) {
        try {
            return tareaRepository.save(nuevaTarea);
        } catch (Exception e) {
            System.out.println("Error al crear la tarea: " + e.getMessage());
            return null;
        }
    }

    // --- 2. ENDPOINT PARA VER TODAS LAS TAREAS (GET) ---
    @GetMapping
    public List<Tarea> obtenerTareas() {
        try {
            return tareaRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener las tareas: " + e.getMessage());
            return null;
        }
    }

    // --- 3. ENDPOINT PARA ACTUALIZAR UNA TAREA (PUT) ---
    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea detallesTarea) {
        try {
            if (tareaRepository.existsById(id)) {
                
                Tarea tareaExistente = tareaRepository.findById(id).get();

                // Actualizamos los campos de la tarea
                tareaExistente.setTitulo(detallesTarea.getTitulo());
                tareaExistente.setDescripcion(detallesTarea.getDescripcion());
                tareaExistente.setFechaVencimiento(detallesTarea.getFechaVencimiento());
                tareaExistente.setEstado(detallesTarea.getEstado());
                
                // Actualizamos a qué materia pertenece
                tareaExistente.setMateria(detallesTarea.getMateria());

                return tareaRepository.save(tareaExistente);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la tarea: " + e.getMessage());
            return null;
        }
    }

    // --- 4. ENDPOINT PARA ELIMINAR UNA TAREA (DELETE) ---
    @DeleteMapping("/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        try {
            if (tareaRepository.existsById(id)) {
                tareaRepository.deleteById(id);
                return "Tarea eliminada correctamente";
            } else {
                return "La tarea no existe en la base de datos";
            }
        } catch (Exception e) {
            return "Ocurrió un error al intentar eliminar: " + e.getMessage();
        }
    }
}