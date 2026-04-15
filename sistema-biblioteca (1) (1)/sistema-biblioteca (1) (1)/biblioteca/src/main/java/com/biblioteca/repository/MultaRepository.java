package com.biblioteca.repository;

import com.biblioteca.model.Multa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultaRepository extends MongoRepository<Multa, String> {
    List<Multa> findByUsuarioId(String usuarioId);
    List<Multa> findByPaga(boolean paga);
    List<Multa> findByEmprestimoId(String emprestimoId);
}
