package com.example.leoncitowebpro.dao;

import java.util.List;
import java.util.Optional;

import com.example.leoncitowebpro.model.Categoria;



public interface CategoriaDAO {
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    Optional<Categoria> findByNombre(String nombre);  // Nuevo para validación duplicados
    Categoria save(Categoria categoria);
    void deleteById(Long id);
}