package com.biblioteca.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "livros")
public class Livro {

    @Id
    private String id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String categoria;
    private int quantidadeDisponivel;

    public Livro() {}

    public Livro(String titulo, String autor, int anoPublicacao, String categoria, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }

    public boolean verificarDisponibilidade() {
        return quantidadeDisponivel > 0;
    }

    public void diminuirQuantidade() {
        if (quantidadeDisponivel > 0) quantidadeDisponivel--;
    }

    public void aumentarQuantidade() {
        quantidadeDisponivel++;
    }
}
