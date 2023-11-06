package com.tienda.carrito.dao.impl;

import com.tienda.carrito.dao.UsuarioDAO;
import com.tienda.carrito.dao.entity.UsuarioEntity;
import com.tienda.carrito.dao.repository.UsuarioRepositorio;
import com.tienda.carrito.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UsuarioDTO buscarPorNombre(String nombre) {
        UsuarioEntity ue = usuarioRepositorio.findByNomUsuario(nombre);
        UsuarioDTO udto = new UsuarioDTO(ue.getIdUsuario(), ue.getNomUsuario(),
                ue.getClaUsuario(), ue.getNomComUsuario());
        return udto;
    }
}
