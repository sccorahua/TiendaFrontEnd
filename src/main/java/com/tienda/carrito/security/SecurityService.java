package com.tienda.carrito.security;

import com.tienda.carrito.dao.UsuarioDAO;
import com.tienda.carrito.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        UsuarioDTO ue = usuarioDAO.buscarPorNombre(nombre);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        UserDetails udet = new User(ue.getNomUsuario(), ue.getClaUsuario(), roles);
        return udet;
    }

}
