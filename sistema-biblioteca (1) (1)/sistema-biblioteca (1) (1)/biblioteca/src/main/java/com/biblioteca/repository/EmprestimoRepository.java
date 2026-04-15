package com.biblioteca.repository;

import com.biblioteca.model.Emprestimo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends MongoRepository<Emprestimo, String> {
    List<Emprestimo> findByUsuarioId(String usuarioId);
    List<Emprestimo> findByLivroId(String livroId);
    List<Emprestimo> findByStatus(String status);
    List<Emprestimo> findByUsuarioIdAndStatus(String usuarioId, String status);
}
