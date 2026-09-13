package com.agenciaviagem.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * DTO usado no endpoint de avaliacao: o cliente manda apenas a nota (1 a 10)
 * que ele quer dar para aquele destino.
 */
public class AvaliacaoRequestDTO {

    @NotNull(message = "A nota e obrigatoria")
    @DecimalMin(value = "1.0", message = "A nota minima e 1")
    @DecimalMax(value = "10.0", message = "A nota maxima e 10")
    private Double nota;

    public AvaliacaoRequestDTO() {
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
