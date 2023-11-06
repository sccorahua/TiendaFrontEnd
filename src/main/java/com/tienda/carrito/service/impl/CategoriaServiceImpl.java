package com.tienda.carrito.service.impl;

import com.tienda.carrito.dto.CategoriaDTO;
import com.tienda.carrito.service.CategoriaService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Value("${backend.restURL}")
    private String backendURL;
    HttpEntity<String> request;
    HttpHeaders headers;

    public CategoriaServiceImpl() {
        String username = "usrServer";
        String password = "usrClave";
        headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        request = new HttpEntity<>(headers);
    }

    @Override
    public List<CategoriaDTO> listarTodos() {
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<CategoriaDTO[]> resultado = plantilla.exchange(backendURL
                + "/categorias",
                HttpMethod.GET, request, CategoriaDTO[].class);
        CategoriaDTO[] lista = resultado.getBody();
        List<CategoriaDTO> retorno = Arrays.asList(lista);
        return retorno;
    }
}
