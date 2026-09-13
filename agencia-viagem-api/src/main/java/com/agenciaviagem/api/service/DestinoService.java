package com.agenciaviagem.api.service;

import com.agenciaviagem.api.dto.DestinoRequestDTO;
import com.agenciaviagem.api.model.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Camada de SERVICO: e aqui que mora a "receita" / regra de negocio.
 * Pensando na analogia do restaurante:
 *   - Controller = garcom (recebe o pedido do cliente, devolve o prato pronto)
 *   - Service    = cozinheiro (sabe o passo a passo, decide como preparar)
 *   - Repository/Banco = despensa (aqui substituida por uma lista em memoria,
 *     porque o desafio pede para NAO usarmos banco de dados)
 *
 * O Controller nunca deve conter regra de negocio - ele so chama o Service.
 */
@Service
public class DestinoService {

    // "Banco de dados" em memoria: enquanto a aplicacao estiver rodando,
    // os destinos cadastrados ficam guardados aqui.
    private final Map<Long, Destino> destinos = new ConcurrentHashMap<>();

    // Gera ids unicos e crescentes (1, 2, 3...) automaticamente.
    private final AtomicLong proximoId = new AtomicLong(1);

    /**
     * 1) Cadastra um novo destino de viagem a partir dos dados recebidos.
     */
    public Destino cadastrar(DestinoRequestDTO dados) {
        Long id = proximoId.getAndIncrement();
        Destino novoDestino = new Destino(id, dados.getNome(), dados.getLocalizacao(), dados.getDescricao());
        destinos.put(id, novoDestino);
        return novoDestino;
    }

    /**
     * 2) Lista todos os destinos cadastrados.
     */
    public List<Destino> listarTodos() {
        return new ArrayList<>(destinos.values());
    }

    /**
     * 3) Pesquisa destinos cujo nome OU localizacao contenha o termo buscado
     * (ignorando maiusculas/minusculas). Se algum parametro vier vazio/nulo,
     * ele simplesmente nao entra no filtro.
     */
    public List<Destino> pesquisar(String nome, String localizacao) {
        return destinos.values().stream()
                .filter(destino -> nome == null || nome.isBlank()
                        || destino.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(destino -> localizacao == null || localizacao.isBlank()
                        || destino.getLocalizacao().toLowerCase().contains(localizacao.toLowerCase()))
                .toList();
    }

    /**
     * 4) Busca um destino especifico pelo id.
     * Retorna Optional porque o destino pode nao existir - quem decide o
     * que fazer nesse caso (404?) e o Controller.
     */
    public Optional<Destino> buscarPorId(Long id) {
        return Optional.ofNullable(destinos.get(id));
    }

    /**
     * 5) Recebe uma nova nota (1 a 10) e recalcula a media do destino,
     * usando a formula classica de media incremental:
     *
     *   novaMedia = (mediaAtual * quantidadeAtual + notaNova) / (quantidadeAtual + 1)
     */
    public Optional<Destino> avaliar(Long id, double nota) {
        Destino destino = destinos.get(id);
        if (destino == null) {
            return Optional.empty();
        }

        int quantidadeAtual = destino.getQuantidadeAvaliacoes();
        double mediaAtual = destino.getNotaMedia();

        double novaMedia = (mediaAtual * quantidadeAtual + nota) / (quantidadeAtual + 1);

        destino.setNotaMedia(novaMedia);
        destino.setQuantidadeAvaliacoes(quantidadeAtual + 1);

        return Optional.of(destino);
    }

    /**
     * 6) Exclui um destino pelo id.
     * Retorna true se havia um destino para remover, false se o id nao existia.
     */
    public boolean excluir(Long id) {
        return destinos.remove(id) != null;
    }
}
