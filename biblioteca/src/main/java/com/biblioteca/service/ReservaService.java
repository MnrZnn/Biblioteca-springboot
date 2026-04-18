package com.biblioteca.service;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Reserva;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarTodos() {
        return reservaRepository.findAll();
    }

    public List<Reserva> listarAtivas() {
        return reservaRepository.findByStatus("ATIVA");
    }

    public Optional<Reserva> buscarPorId(String id) {
        return reservaRepository.findById(id);
    }

    /**
     * Cria reserva com status ATIVA, válida 7 dias.
     * reservaDAO.inserir() → INSERT reserva
     */
    public Reserva reservar(Usuario usuario, Livro livro) {
        Reserva reserva = new Reserva(
            usuario.getId(), usuario.getNome(),
            livro.getId(), livro.getTitulo()
        );
        return reservaRepository.save(reserva);
    }

    /**
     * Cancela reserva: status → CANCELADA
     * reservaDAO.cancelar() → UPDATE reserva
     */
    public Reserva cancelar(String reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId)
            .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
        reserva.cancelar();
        return reservaRepository.save(reserva);
    }
}
