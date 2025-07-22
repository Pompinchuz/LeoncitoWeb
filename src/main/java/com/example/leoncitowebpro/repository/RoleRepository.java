package com.example.leoncitowebpro.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leoncitowebpro.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNombre(String nombre);  // Método personalizado para buscar por nombre (ej: "ADMIN")
}