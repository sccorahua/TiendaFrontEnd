package com.tienda.carrito.controller;

import com.tienda.carrito.dto.ProductoDTO;
import com.tienda.carrito.service.CategoriaService;
import com.tienda.carrito.service.ProductoService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping("productoMantLista")
    public ModelAndView mostrarMantProducto() {
        ModelAndView mv = new ModelAndView("mantProductos", "lista", 
                productoService.listarTodos());
        mv.addObject("productoDatos", new ProductoDTO());
        mv.addObject("listaCategoria", categoriaService.listarTodos());
        return mv;
    }

    @RequestMapping("productoGrabar")
    public ModelAndView productoGrabar(@ModelAttribute("productoDatos") ProductoDTO producto,
            HttpServletRequest request) {

        producto.setNueProducto(request.getParameter("chkNuevo") == null ? 0 : 1);

        if (producto.getIdProducto() == 0) {
            productoService.insertarProducto(producto);
        } else {
            productoService.modificarProducto(producto);
        }
        return new ModelAndView("redirect:productoMantLista");
    }

    @RequestMapping("productoModificar")
    public ModelAndView mostrarUnicoProducto(@RequestParam("codigo") int codigo) {
        ProductoDTO pdto = productoService.obtenerUno(codigo);
        ModelAndView mv = new ModelAndView("mantProductos", "lista", productoService.listarTodos());
        mv.addObject("productoDatos", pdto);
        mv.addObject("listaCategoria", categoriaService.listarTodos());
        return mv;
    }

    @RequestMapping("productoEliminar")
    public ModelAndView eliminarProducto(@RequestParam("codigo") int codigo) {
        productoService.eliminarProducto(codigo);
        return new ModelAndView("redirect:productoMantLista");
    }
}
