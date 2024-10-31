package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Usuario;
import com.example.integrador.controlador.dto.UsuarioRegistroDTO;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;




public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
}
