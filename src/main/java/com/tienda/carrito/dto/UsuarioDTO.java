package com.tienda.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private int idUsuario;
    private String nomUsuario;
    private String claUsuario;
    private String nomComUsuario;
}
