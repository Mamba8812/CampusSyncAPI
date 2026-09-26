package com.example.campussync.repository;
//caja de herramientas de Spring que contiene todas las operaciones SQL preprogramadas para comunicarse con PostgreSQL.
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campussync.model.Usuario;//importamos la clase Usuario para poder usarla en el repositorio

//creamos clase publica UsuarioRepository que hereda de JpaRepository, para poder usar todas las operaciones SQL preprogramadas de Spring
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Al heredar de JpaRepository, Spring nos regala automáticamente métodos como:
    // save() -> para guardar un usuario
    // findAll() -> para traer todos los usuarios
    // deleteById() -> para borrar un usuario
    
}