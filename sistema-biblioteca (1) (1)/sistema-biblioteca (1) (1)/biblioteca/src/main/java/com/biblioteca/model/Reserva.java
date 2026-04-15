package com.biblioteca.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id;
    private String usuarioId;
    private String usuarioNome;
    private String livroId;
    private String livroTitulo;
    private LocalDate dataSolicitacao;
    private LocalDate dataExpiracao;
    private String status; // ATIVA, CANCELADA, EXPIRADA

    public Reserva() {}

    public Reserva(String usuarioId, String usuarioNome, String livroId, String livroTitulo) {
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.livroId = livroId;
        this.livroTitulo = livroTitulo;
        this.dataSolicitacao = LocalDate.now();
        this.dataExpiracao = LocalDate.now().plusDays(7);
        this.status = "ATIVA";
    }

    public void cancelar() {
        this.status = "CANCELADA";
    }

    public boolean validarReserva() {
        return "ATIVA".equals(status) && LocalDate.now().isBefore(dataExpiracao);
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }
    public String getLivroId() { return livroId; }
    public void setLivroId(String livroId) { this.livroId = livroId; }
    public String getLivroTitulo() { return livroTitulo; }
    public void setLivroTitulo(String livroTitulo) { this.livroTitulo = livroTitulo; }
    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    public LocalDate getDataExpiracao() { return dataExpiracao; }
    public void setDataExpiracao(LocalDate dataExpiracao) { this.dataExpiracao = dataExpiracao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
