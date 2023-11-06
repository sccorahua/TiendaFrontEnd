package com.tienda.carrito.controller;

import com.tienda.carrito.dto.ProductoDTO;
import com.tienda.carrito.service.CategoriaService;
import com.tienda.carrito.service.ProductoService;
import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping("/")
    public ModelAndView mostrarIndex() {
        ModelAndView mv = new ModelAndView("index", "listaNuevos",
                productoService.listarNuevos());
        mv.addObject("listaCategorias", categoriaService.listarTodos());
        return mv;
    }

    @RequestMapping("/nuevosCategoria")
    public ModelAndView mostrarNuevosCategoria(@RequestParam int idCategoria) {
        ModelAndView mv = new ModelAndView("index", "listaNuevos",
                productoService.listarNuevosPorCategoria(idCategoria));
        mv.addObject("listaCategorias", categoriaService.listarTodos());
        return mv;
    }

    @RequestMapping(value = "/carritoVer", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String carritoVer(@RequestParam String productos) {

        String listaPro[] = productos.split(",");
        StringBuilder rpta = new StringBuilder("");
        double totalCompras = 0;

        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");

        rpta.append("<div class=\"cart-list\">");
        for (int i = 0; i < listaPro.length; i++) {
            String producto = listaPro[i];
            ProductoDTO dto = productoService.obtenerUno(Integer.parseInt(producto));
            rpta.append("<div class=\"product-widget\">");
            rpta.append("<div class=\"product-img\">");
            rpta.append("<img src=\"").append(dto.getImgProducto()).append("\"alt =\"\">");
            rpta.append("</div>");
            rpta.append("<div class=\"product-body\">");
            rpta.append("<h3 class=\"product-name\"><a href =\"#\">").append(dto.getNomProducto()).append("</a></h3>");
            rpta.append("<h4 class=\"product-price\"><span class=\"qty\">1x</span>$").
                    append(decimalFormat.format(dto.getPreProducto())).append("</h4>");
            rpta.append("</div>");
            rpta.append("<button class=\"delete\"><i class=\"fa fa-close\"></i></button>");
            rpta.append("</div>");
            totalCompras = totalCompras + dto.getPreProducto();
        }
        rpta.append("</div>");

        rpta.append("<div class=\"cart-summary\">");
        rpta.append("<small>").append(listaPro.length).append(" Item(s) seleccionados</small >");
        rpta.append("<h5>SUBTOTAL:$").append(decimalFormat.format(totalCompras)).append("</h5>");
        rpta.append("</div>");
        rpta.append("<div class=\"cart-btns\">");
        rpta.append("<a href=\"#\">Ver Carrito</a>");
        rpta.append("<a href=\"#\">Pagar! <i class=\"fa fa-arrow-circleright\"></i></a>");
        rpta.append("</div>");

        return rpta.toString();
    }
}
