package com.example.campussync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campussync.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}