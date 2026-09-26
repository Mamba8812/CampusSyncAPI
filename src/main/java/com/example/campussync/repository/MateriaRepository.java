package com.example.campussync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campussync.model.Materia;

//creamos una clase tipo contrato que define las operaciones que se pueden realizar con la entidad Materia en la base de datos.
// Esta interfaz hereda de JpaRepository, lo que nos permite usar todas las operaciones SQL preprogramadas de Spring para la entidad Materia.
//el JpaRepository recibe dos parametros: el tipo de la entidad (Materia) y el tipo de la clave primaria (Long).
public interface MateriaRepository extends JpaRepository<Materia, Long> {
}