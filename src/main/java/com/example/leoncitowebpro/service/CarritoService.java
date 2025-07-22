package com.example.leoncitowebpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leoncitowebpro.model.Carrito;
import com.example.leoncitowebpro.model.CarritoItem;
import com.example.leoncitowebpro.model.Producto;
import com.example.leoncitowebpro.model.Usuario;
import com.example.leoncitowebpro.repository.CarritoRepository;
import com.example.leoncitowebpro.repository.ProductoRepository;
import com.example.leoncitowebpro.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Carrito getOrCreateCarrito(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Optional<Carrito> carritoOpt = carritoRepository.findByUsuario(usuario);
        if (carritoOpt.isPresent()) {
            return carritoOpt.get();
        } else {
            Carrito nuevoCarrito = new Carrito();
            nuevoCarrito.setUsuario(usuario);
            return save(nuevoCarrito);
        }
    }

    public Carrito agregarProducto(Long usuarioId, Long productoId, int cantidad) {
        Carrito carrito = getOrCreateCarrito(usuarioId);
        Producto producto = productoRepository.findById(productoId).orElseThrow();
        carrito.agregarProducto(producto, cantidad);
        return save(carrito);
    }

    public Carrito getCarrito(Long usuarioId) {
        Carrito carrito = getOrCreateCarrito(usuarioId);
        // Force load items and products to avoid lazy loading in JSON
        carrito.getItems().forEach(item -> {
            item.getProducto().getTitulo();  // Trigger load
            item.getProducto().getPrecio();
            if (item.getProducto().getCategoria() != null) {
                item.getProducto().getCategoria().getNombre();  // If needed
            }
        });
        return carrito;
    }

    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }
}