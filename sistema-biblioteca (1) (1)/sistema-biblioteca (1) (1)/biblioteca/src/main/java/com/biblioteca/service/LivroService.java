package com.biblioteca.service;

import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(String id) {
        return livroRepository.findById(id);
    }

    public Livro inserir(Livro livro) {
        if (livro.getId() != null && livro.getId().isBlank()) {
            livro.setId(null);
        }
        return livroRepository.save(livro);
    }

    public Livro atualizar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void remover(String id) {
        livroRepository.deleteById(id);
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarDisponiveis() {
        return livroRepository.findByQuantidadeDisponivelGreaterThan(0);
    }

    public void atualizarQuantidade(String livroId, int delta) {
        livroRepository.findById(livroId).ifPresent(livro -> {
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + delta);
            livroRepository.save(livro);
        });
    }
}
