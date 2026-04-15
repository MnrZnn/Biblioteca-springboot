package com.biblioteca.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String matricula;
    private String endereco;
    private String tipo; // ALUNO, PROFESSOR, FUNCIONARIO

    public Usuario() {}

    public Usuario(String nome, String matricula, String endereco, String tipo) {
        this.nome = nome;
        this.matricula = matricula;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
