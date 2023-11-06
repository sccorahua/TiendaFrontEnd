package com.tienda.carrito.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class UsuarioEntity {

    @Id
    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "nomusuario", nullable = false)
    private String nomUsuario;

    @Column(name = "clausuario")
    private String claUsuario;

    @Column(name = "nomcomusuario")
    private String nomComUsuario;
}
