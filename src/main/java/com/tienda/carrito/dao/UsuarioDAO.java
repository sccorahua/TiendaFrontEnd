package com.tienda.carrito.dao;

import com.tienda.carrito.dto.UsuarioDTO;

public interface UsuarioDAO {

    public UsuarioDTO buscarPorNombre(String nombre);

}
