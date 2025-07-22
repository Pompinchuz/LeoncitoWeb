package com.example.leoncitowebpro.dao;

import java.util.List;
import java.util.Optional;

import com.example.leoncitowebpro.model.Producto;

public interface ProductoDAO {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    List<Producto> findByCategoriaId(Long categoriaId);
    Producto save(Producto producto);
    void deleteById(Long id);
}