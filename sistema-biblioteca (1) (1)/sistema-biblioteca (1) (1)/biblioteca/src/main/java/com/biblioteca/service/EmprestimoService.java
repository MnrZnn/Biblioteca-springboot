package com.biblioteca.service;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Multa;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroService livroService;

    @Autowired
    private MultaRepository multaRepository;

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> buscarPorId(String id) {
        return emprestimoRepository.findById(id);
    }

    public List<Emprestimo> listarAtivos() {
        return emprestimoRepository.findByStatus("ATIVO");
    }

    public List<Emprestimo> listarPorUsuario(String usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Realiza empréstimo conforme diagrama de sequência:
     * empDAO.inserir(emp, uid, lid) → INSERT emprestimo
     * livroDAO.atualizarQuantidade() → UPDATE livro
     */
    public Emprestimo emprestar(Usuario usuario, Livro livro) {
        if (!livro.verificarDisponibilidade()) {
            throw new IllegalStateException("Livro indisponível para empréstimo");
        }

        Emprestimo emprestimo = new Emprestimo(
            usuario.getId(), usuario.getNome(),
            livro.getId(), livro.getTitulo()
        );

        // INSERT emprestimo
        Emprestimo salvo = emprestimoRepository.save(emprestimo);

        // UPDATE livro - diminuir quantidade
        livroService.atualizarQuantidade(livro.getId(), -1);

        return salvo;
    }

    /**
     * Finaliza devolução conforme diagrama de sequência:
     * empDAO.finalizarEmprestimo(emp) → UPDATE emprestimo
     * [opt: atraso detectado] multaDAO.inserir(multa) → INSERT multa
     * livroDAO.atualizarQuantidade() → UPDATE livro
     */
    public Emprestimo devolver(String emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));

        long diasAtraso = emprestimo.getDiasAtraso();
        emprestimo.finalizarEmprestimo();

        // UPDATE emprestimo
        Emprestimo finalizado = emprestimoRepository.save(emprestimo);

        // [opt: atraso detectado] INSERT multa
        if (diasAtraso > 0) {
            Multa multa = new Multa(
                emprestimo.getId(),
                emprestimo.getUsuarioId(),
                emprestimo.getUsuarioNome(),
                (int) diasAtraso
            );
            multaRepository.save(multa);
        }

        // UPDATE livro - aumentar quantidade
        livroService.atualizarQuantidade(emprestimo.getLivroId(), 1);

        return finalizado;
    }
}
