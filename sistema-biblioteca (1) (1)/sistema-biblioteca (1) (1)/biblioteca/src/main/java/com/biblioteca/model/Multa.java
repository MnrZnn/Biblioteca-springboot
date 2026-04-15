package com.biblioteca.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "multas")
public class Multa {

    @Id
    private String id;
    private String emprestimoId;
    private String usuarioId;
    private String usuarioNome;
    private int diasAtraso;
    private double valor;
    private boolean paga;

    private static final double VALOR_POR_DIA = 2.50;

    public Multa() {}

    public Multa(String emprestimoId, String usuarioId, String usuarioNome, int diasAtraso) {
        this.emprestimoId = emprestimoId;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.diasAtraso = diasAtraso;
        this.valor = calcularValor();
        this.paga = false;
    }

    public double calcularValor() {
        return diasAtraso * VALOR_POR_DIA;
    }

    public long getDiasAtraso() { return diasAtraso; }
    public void setDiasAtraso(int diasAtraso) { this.diasAtraso = diasAtraso; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmprestimoId() { return emprestimoId; }
    public void setEmprestimoId(String emprestimoId) { this.emprestimoId = emprestimoId; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public boolean isPaga() { return paga; }
    public void setPaga(boolean paga) { this.paga = paga; }
}
