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

import com.example.campussync.model.Apunte;
import com.example.campussync.repository.ApunteRepository;

@RestController
@RequestMapping("/api/apuntes")
public class ApunteController {

private final ApunteRepository apunteRepository;

public ApunteController(ApunteRepository apunteRepository) {
    this.apunteRepository = apunteRepository;
}

    // --- 1. ENDPOINT PARA CREAR UN APUNTE (POST) ---
    @PostMapping
    public Apunte crearApunte(@RequestBody Apunte nuevoApunte) {
        try {
            return apunteRepository.save(nuevoApunte);
        } catch (Exception e) {
            System.out.println("Error al crear el apunte: " + e.getMessage());
            return null;
        }
    }

    // --- 2. ENDPOINT PARA VER TODOS LOS APUNTES (GET) ---
    @GetMapping
    public List<Apunte> obtenerApuntes() {
        try {
            return apunteRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener los apuntes: " + e.getMessage());
            return null;
        }
    }

    // --- 3. ENDPOINT PARA ACTUALIZAR UN APUNTE (PUT) ---
    @PutMapping("/{id}")
    public Apunte actualizarApunte(@PathVariable Long id, @RequestBody Apunte detallesApunte) {
        try {
            if (apunteRepository.existsById(id)) {
                
                Apunte apunteExistente = apunteRepository.findById(id).get();

                // Actualizamos los campos
                apunteExistente.setTitulo(detallesApunte.getTitulo());
                apunteExistente.setContenido(detallesApunte.getContenido());
                apunteExistente.setFechaCreacion(detallesApunte.getFechaCreacion());
                
                // Actualizamos a qué materia pertenece
                apunteExistente.setMateria(detallesApunte.getMateria());

                return apunteRepository.save(apunteExistente);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el apunte: " + e.getMessage());
            return null;
        }
    }

    // --- 4. ENDPOINT PARA ELIMINAR UN APUNTE (DELETE) ---
    @DeleteMapping("/{id}")
    public String eliminarApunte(@PathVariable Long id) {
        try {
            if (apunteRepository.existsById(id)) {
                apunteRepository.deleteById(id);
                return "Apunte eliminado correctamente";
            } else {
                return "El apunte no existe en la base de datos";
            }
        } catch (Exception e) {
            return "Ocurrió un error al intentar eliminar: " + e.getMessage();
        }
    }
}