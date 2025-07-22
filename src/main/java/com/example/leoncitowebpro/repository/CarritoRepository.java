package com.example.leoncitowebpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leoncitowebpro.model.Carrito;
import com.example.leoncitowebpro.model.Usuario;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuario(Usuario usuario);
}