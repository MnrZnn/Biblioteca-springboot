package com.biblioteca.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "emprestimos")
public class Emprestimo {

    @Id
    private String id;
    private String usuarioId;
    private String usuarioNome;
    private String livroId;
    private String livroTitulo;
    private LocalDate dataRetirada;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    private String status; // ATIVO, FINALIZADO, ATRASADO

    public Emprestimo() {}

    public Emprestimo(String usuarioId, String usuarioNome, String livroId, String livroTitulo) {
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.livroId = livroId;
        this.livroTitulo = livroTitulo;
        this.dataRetirada = LocalDate.now();
        this.dataPrevista = LocalDate.now().plusDays(14);
        this.status = "ATIVO";
    }

    public void finalizarEmprestimo() {
        this.dataDevolucao = LocalDate.now();
        if (LocalDate.now().isAfter(dataPrevista)) {
            this.status = "ATRASADO";
        } else {
            this.status = "FINALIZADO";
        }
    }

    public boolean isAtrasado() {
        return LocalDate.now().isAfter(dataPrevista) && "ATIVO".equals(status);
    }

    public long getDiasAtraso() {
        if (dataDevolucao != null && dataDevolucao.isAfter(dataPrevista)) {
            return dataPrevista.until(dataDevolucao).getDays();
        }
        if ("ATIVO".equals(status) && LocalDate.now().isAfter(dataPrevista)) {
            return dataPrevista.until(LocalDate.now()).getDays();
        }
        return 0;
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
    public LocalDate getDataRetirada() { return dataRetirada; }
    public void setDataRetirada(LocalDate dataRetirada) { this.dataRetirada = dataRetirada; }
    public LocalDate getDataPrevista() { return dataPrevista; }
    public void setDataPrevista(LocalDate dataPrevista) { this.dataPrevista = dataPrevista; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
