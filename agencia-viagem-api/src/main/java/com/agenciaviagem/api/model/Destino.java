package com.agenciaviagem.api.model;

/**
 * Representa um destino de viagem.
 *
 * Como o desafio pede para NAO nos preocuparmos com banco de dados,
 * este objeto vive apenas na memoria (guardado dentro do Service, em um Map).
 */
public class Destino {

    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;

    // Controle da avaliacao media (nota de 1 a 10)
    private double notaMedia;
    private int quantidadeAvaliacoes;

    public Destino() {
    }

    public Destino(Long id, String nome, String localizacao, String descricao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.notaMedia = 0.0;
        this.quantidadeAvaliacoes = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public void setQuantidadeAvaliacoes(int quantidadeAvaliacoes) {
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
    }
}
