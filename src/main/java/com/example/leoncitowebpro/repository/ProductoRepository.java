package com.example.leoncitowebpro.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leoncitowebpro.model.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaId(Long categoriaId);  // Ejemplo de método personalizado
}