package com.agenciaviagem.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicacao.
 * Ao rodar este main(), o Spring Boot "liga o restaurante inteiro":
 * sobe o servidor (Tomcat embutido) e registra todos os Controllers e Services.
 */
@SpringBootApplication
public class AgenciaViagemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgenciaViagemApiApplication.class, args);
    }
}
