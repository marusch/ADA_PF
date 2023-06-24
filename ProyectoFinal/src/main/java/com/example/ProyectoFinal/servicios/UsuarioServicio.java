package com.example.ProyectoFinal.servicios;

import java.util.List;

import com.example.ProyectoFinal.dto.UsuarioRegistroDTO;
import com.example.ProyectoFinal.entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServicio extends UserDetailsService{
    
    public Usuario guardar(UsuarioRegistroDTO registroDTO);


}
