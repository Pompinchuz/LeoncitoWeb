package com.example.leoncitowebpro.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leoncitowebpro.model.Role;
import com.example.leoncitowebpro.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
    List<Usuario> findByRol(Role rol);  // Método adicional para listar por rol (útil para admin)
}