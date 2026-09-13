package com.agenciaviagem.api.controller;

import com.agenciaviagem.api.dto.AvaliacaoRequestDTO;
import com.agenciaviagem.api.dto.DestinoRequestDTO;
import com.agenciaviagem.api.model.Destino;
import com.agenciaviagem.api.service.DestinoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller = "garcom": recebe a requisicao HTTP, repassa para o Service
 * (o cozinheiro) e devolve a resposta pronta para o cliente. Nenhuma regra
 * de negocio deve aparecer aqui - so orquestracao e codigos HTTP.
 */
@RestController
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService destinoService;

    // Injecao de dependencia via construtor: o Spring cria o Service
    // automaticamente e "entrega" ele pronto para o Controller usar.
    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    // 1) POST /destinos -> cadastra um novo destino
    @PostMapping
    public ResponseEntity<Destino> cadastrar(@Valid @RequestBody DestinoRequestDTO dados) {
        Destino destinoCriado = destinoService.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(destinoCriado);
    }

    // 2) GET /destinos -> lista todos os destinos
    @GetMapping
    public ResponseEntity<List<Destino>> listarTodos() {
        return ResponseEntity.ok(destinoService.listarTodos());
    }

    // 3) GET /destinos/pesquisa?nome=...&localizacao=... -> pesquisa por nome/localizacao
    @GetMapping("/pesquisa")
    public ResponseEntity<List<Destino>> pesquisar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao) {
        return ResponseEntity.ok(destinoService.pesquisar(nome, localizacao));
    }

    // 4) GET /destinos/{id} -> detalhes de um destino especifico
    @GetMapping("/{id}")
    public ResponseEntity<Destino> buscarPorId(@PathVariable Long id) {
        return destinoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5) PATCH /destinos/{id}/avaliacoes -> registra uma nova avaliacao (1 a 10)
    @PatchMapping("/{id}/avaliacoes")
    public ResponseEntity<Destino> avaliar(@PathVariable Long id, @Valid @RequestBody AvaliacaoRequestDTO dados) {
        return destinoService.avaliar(id, dados.getNota())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 6) DELETE /destinos/{id} -> exclui um destino
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean removido = destinoService.excluir(id);
        return removido
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
