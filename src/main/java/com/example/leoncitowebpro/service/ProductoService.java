package com.example.leoncitowebpro.service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.leoncitowebpro.dao.ProductoDAO;
import com.example.leoncitowebpro.model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class ProductoService {

    @Autowired
    private ProductoDAO productoDAO;  // Cambia a DAO

    public List<Producto> listarTodos() {
        return productoDAO.findAll();
    }

    // Nuevo método para filtrar (lógica en service, data from DAO)
    public List<Producto> filtrar(String search, Long categoriaId) {
        List<Producto> productos = productoDAO.findAll();
        if (search != null && !search.isEmpty()) {
            productos = productos.stream()
                    .filter(p -> p.getTitulo().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (categoriaId != null) {
            productos = productos.stream()
                    .filter(p -> p.getCategoria() != null && p.getCategoria().getId().equals(categoriaId))
                    .collect(Collectors.toList());
        }
        return productos;
    }

    public Producto crear(@Valid Producto producto) {
        return productoDAO.save(producto);
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoDAO.findById(id);
    }

    public Producto actualizar(Long id, @Valid Producto productoActualizado) {
        Optional<Producto> productoExistente = productoDAO.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setTitulo(productoActualizado.getTitulo());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setValoracion(productoActualizado.getValoracion());
            producto.setImagenUrl(productoActualizado.getImagenUrl());
            producto.setCategoria(productoActualizado.getCategoria());
            return productoDAO.save(producto);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    public void eliminar(Long id) {
        productoDAO.deleteById(id);
    }
}