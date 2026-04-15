package com.biblioteca.repository;

import com.biblioteca.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByMatricula(String matricula);
    List<Usuario> findByTipo(String tipo);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
