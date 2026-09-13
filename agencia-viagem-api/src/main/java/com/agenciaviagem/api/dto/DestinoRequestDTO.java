package com.agenciaviagem.api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO = "Data Transfer Object". E o formato de dado que o CLIENTE da API
 * envia no corpo (body) da requisicao ao cadastrar um novo destino.
 *
 * Por que nao usar a classe Destino direto? Porque quem cadastra nao deve
 * poder inventar um id, uma nota media ou uma quantidade de avaliacoes -
 * isso e controlado pelo nosso Service (o "cozinheiro").
 */
public class DestinoRequestDTO {

    @NotBlank(message = "O nome do destino e obrigatorio")
    private String nome;

    @NotBlank(message = "A localizacao do destino e obrigatoria")
    private String localizacao;

    private String descricao;

    public DestinoRequestDTO() {
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
}
