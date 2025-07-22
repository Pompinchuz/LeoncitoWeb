package com.example.leoncitowebpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leoncitowebpro.model.Categoria;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c")  // JPQL manual para listar todas
    List<Categoria> findAllManual();

    @Query("SELECT c FROM Categoria c WHERE c.id = :id")  // JPQL manual para findById
    Optional<Categoria> findByIdManual(@Param("id") Long id);

    @Query("SELECT c FROM Categoria c WHERE LOWER(c.nombre) = LOWER(:nombre)")  // JPQL manual para buscar por nombre (case-insensitive)
    Optional<Categoria> findByNombreManual(@Param("nombre") String nombre);

    // Mantén save y deleteById de JpaRepository para simplicidad
}