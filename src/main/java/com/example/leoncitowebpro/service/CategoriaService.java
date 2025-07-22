package com.example.leoncitowebpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leoncitowebpro.dao.CategoriaDAO;
import com.example.leoncitowebpro.model.Categoria;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaDAO categoriaDAO;  // Usa DAO

    public List<Categoria> listarTodas() {
        return categoriaDAO.findAll();
    }

    public Categoria crear(Categoria categoria) {
        // Verifica si ya existe una categoría con el mismo nombre
        Optional<Categoria> existingCategoria = categoriaDAO.findByNombre(categoria.getNombre());
        if (existingCategoria.isPresent()) {
            throw new IllegalStateException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }
        return categoriaDAO.save(categoria);
    }

    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaDAO.findById(id);
    }

    public Categoria actualizar(Long id, Categoria categoriaActualizada) {
        Optional<Categoria> categoriaExistente = categoriaDAO.findById(id);
        if (categoriaExistente.isPresent()) {
            Categoria categoria = categoriaExistente.get();
            categoria.setNombre(categoriaActualizada.getNombre());
            return categoriaDAO.save(categoria);
        } else {
            throw new RuntimeException("Categoría no encontrada");
        }
    }

    public void eliminar(Long id) {
        categoriaDAO.deleteById(id);
    }
}