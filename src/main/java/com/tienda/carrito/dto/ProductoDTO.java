package com.tienda.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private int idProducto;
    private String nomProducto;
    private CategoriaDTO categoria;
    private double preProducto;
    private double antPreProducto;
    private int nueProducto;
    private String imgProducto;
}
