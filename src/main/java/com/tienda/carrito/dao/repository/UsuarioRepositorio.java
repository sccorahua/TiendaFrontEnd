package com.tienda.carrito.dao.repository;

import com.tienda.carrito.dao.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<UsuarioEntity, Integer> {

    public UsuarioEntity findByNomUsuario(String nomUsuario);

}
