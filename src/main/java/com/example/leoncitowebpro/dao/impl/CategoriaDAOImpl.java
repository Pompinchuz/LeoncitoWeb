package com.example.leoncitowebpro.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.leoncitowebpro.dao.CategoriaDAO;
import com.example.leoncitowebpro.model.Categoria;
import com.example.leoncitowebpro.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAllManual();  // Usa @Query manual
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findByIdManual(id);  // Usa @Query manual
    }

    @Override
    public Optional<Categoria> findByNombre(String nombre) {
        return categoriaRepository.findByNombreManual(nombre);  // Usa @Query manual
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);  // Nativo de Jpa
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);  // Nativo de Jpa
    }
}