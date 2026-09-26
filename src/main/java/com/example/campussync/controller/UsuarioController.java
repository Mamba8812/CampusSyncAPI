package com.example.campussync.controller;

//herramienta para crear y manejar Listas
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.campussync.model.Usuario;
import com.example.campussync.repository.UsuarioRepository;

@RestController // 1. Le dice a Spring que esta clase recibirá peticiones web y responderá en formato JSON
@RequestMapping("/api/usuarios") // 2. Define la URL base para acceder a este controlador
public class UsuarioController {

    //creamso un contrcutor para conectar el repositorio de usuarios con el controlador.
    // Esto permite que Spring inyecte automáticamente el repositorio cuando se crea el controlador.
private final UsuarioRepository usuarioRepository;

public UsuarioController(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
}

    // --- ENDPOINT PARA LEER DATOS (GET) ---
    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        // Usa el repositorio para buscar todos los registros en la tabla
        return usuarioRepository.findAll();
    }

    // --- ENDPOINT PARA GUARDAR DATOS (POST) ---
    @PostMapping
    public Usuario crearNuevoUsuario(@RequestBody Usuario usuario) {
        // Toma el usuario que llega en formato JSON y lo guarda en PostgreSQL
        return usuarioRepository.save(usuario);
    }

    // --- ENDPOINT PARA ACTUALIZAR DATOS (PUT) ---

    //indica a Spring Boot que este bloque de código solo debe ejecutarse cuando llegue una petición web de tipo "PUT" (modificar) a una URL que termine con un número identificador.
    @PutMapping("/{id}")

    //creamos metodo que recibe como parametros dos recolestores, @PathVariable Long id(recolecta el numero al final de url para identificar a quien editar) @RequestBody (recolesta el json ingresado en el Body en Thunder Client) Y REGRESA UN DATO TIPO USUARIO
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario detallesUsuario) {
        
       try {
            // Comprobamos si el usuario existe
            if (usuarioRepository.existsById(id)) {
                
                // Lo sacamos de la base de datos
                Usuario usuarioExistente = usuarioRepository.findById(id).get();

                // Reemplazamos los datos viejos con los nuevos
                usuarioExistente.setNombreCompleto(detallesUsuario.getNombreCompleto());
                usuarioExistente.setCorreo(detallesUsuario.getCorreo());
                usuarioExistente.setSemestre(detallesUsuario.getSemestre());

                // Guardamos los cambios
                return usuarioRepository.save(usuarioExistente);
                
            } else {
                // Si no existe, no hacemos nada y devolvemos vacío (null)
                return null;
            }
            
        } catch (Exception e) {
            // Si la base de datos falla, atrapamos el error y lo mostramos en la consola
            System.out.println("Ocurrió un error al actualizar: " + e.getMessage());
            return null;
        }
    }
    // --- ENDPOINT PARA ELIMINAR DATOS (DELETE) ---

    //indica a Spring Boot que este bloque de código solo debe ejecutarse cuando llegue una petición web de tipo "DELETE" (eliminar) a una URL que termine con un número identificador.
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        
        try {
            // Intentamos buscar si el usuario existe
            if (usuarioRepository.existsById(id)) {
                // Si existe, lo borramos directamente usando su ID
                usuarioRepository.deleteById(id);
                return "Usuario eliminado correctamente";
            } else {
                return "El usuario no existe en la base de datos";
            }
            
        } catch (Exception e) {
            // Si algo sale mal con la base de datos, el Catch atrapa el error
            return "Ocurrió un error al intentar eliminar: " + e.getMessage();
        }
    }
}