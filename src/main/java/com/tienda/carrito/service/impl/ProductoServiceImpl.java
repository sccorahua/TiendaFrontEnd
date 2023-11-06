package com.tienda.carrito.service.impl;

import com.tienda.carrito.dto.ProductoDTO;
import com.tienda.carrito.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Value("${backend.restURL}")
    private String backendURL;
    HttpEntity<String> request;
    HttpHeaders headers;

    public ProductoServiceImpl() {
        String username = "usrServer";
        String password = "usrClave";
        headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        request = new HttpEntity<>(headers);
    }

    @Override
    public List<ProductoDTO> listarTodos() {
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<ProductoDTO[]> resultado = plantilla.exchange(backendURL
                + "/productos",
                HttpMethod.GET, request, ProductoDTO[].class);
        ProductoDTO[] lista = resultado.getBody();
        List<ProductoDTO> retorno = Arrays.asList(lista);
        return retorno;
    }

    @Override
    public List<ProductoDTO> listarNuevos() {
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<ProductoDTO[]> resultado = plantilla.exchange(backendURL
                + "/productos/nuevo",
                HttpMethod.GET, request, ProductoDTO[].class);
        ProductoDTO[] lista = resultado.getBody();
        List<ProductoDTO> retorno = Arrays.asList(lista);
        return retorno;
    }

    @Override
    public List<ProductoDTO> listarNuevosPorCategoria(int categoria) {
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<ProductoDTO[]> resultado = plantilla.exchange(backendURL
                + "/productos/nuevo/categoria/" + categoria,
                HttpMethod.GET, request, ProductoDTO[].class);
        ProductoDTO[] lista = resultado.getBody();
        List<ProductoDTO> retorno = Arrays.asList(lista);
        return retorno;
    }

    @Override
    public void insertarProducto(ProductoDTO pd) {
        RestTemplate llamada = new RestTemplate();
        HttpEntity<ProductoDTO> entidad = new HttpEntity<>(pd, headers);
        llamada.exchange(backendURL + "/productos", HttpMethod.POST, entidad,
                ProductoDTO.class);
    }

    @Override
    public void modificarProducto(ProductoDTO pd) {
        RestTemplate llamada = new RestTemplate();
        HttpEntity<ProductoDTO> entidad = new HttpEntity<>(pd, headers);
        llamada.exchange(backendURL + "/productos/" + pd.getIdProducto(), HttpMethod.PUT,
                entidad, ProductoDTO.class);
    }

    @Override
    public void eliminarProducto(int codigo) {
        RestTemplate llamada = new RestTemplate();
        llamada.exchange(backendURL + "/productos/" + codigo,
                HttpMethod.DELETE, request, ProductoDTO.class);
    }

    @Override
    public ProductoDTO obtenerUno(int codigo) {
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<ProductoDTO> resultado = plantilla.exchange(backendURL + "/productos/"
                + codigo,
                HttpMethod.GET, request, ProductoDTO.class);
        ProductoDTO producto = resultado.getBody();
        return producto;
    }
}
