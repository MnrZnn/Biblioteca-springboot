package com.biblioteca.service;

import com.biblioteca.model.Usuario;
import com.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorMatricula(String matricula) {
        return usuarioRepository.findByMatricula(matricula);
    }

    public Usuario inserir(Usuario usuario) {
        if (usuario.getId() != null && usuario.getId().isBlank()) {
            usuario.setId(null);
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void remover(String id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarPorTipo(String tipo) {
        return usuarioRepository.findByTipo(tipo);
    }
}
