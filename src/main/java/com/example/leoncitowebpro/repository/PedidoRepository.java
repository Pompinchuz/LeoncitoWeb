package com.example.leoncitowebpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leoncitowebpro.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}