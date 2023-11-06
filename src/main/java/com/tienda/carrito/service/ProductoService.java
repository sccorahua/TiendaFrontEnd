package com.tienda.carrito.service;

import com.tienda.carrito.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {

    public List<ProductoDTO> listarTodos();

    public List<ProductoDTO> listarNuevos();

    public List<ProductoDTO> listarNuevosPorCategoria(int categoria);

    public void insertarProducto(ProductoDTO pd);

    public void modificarProducto(ProductoDTO pd);

    public void eliminarProducto(int codigo);

    public ProductoDTO obtenerUno(int codigo);
}
