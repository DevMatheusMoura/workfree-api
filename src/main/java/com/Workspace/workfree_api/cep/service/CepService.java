package com.Workspace.workfree_api.cep.service;

import com.Workspace.workfree_api.cep.domain.Address;
import com.Workspace.workfree_api.cep.domain.CepLog;
import com.Workspace.workfree_api.cep.repository.CepLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class CepService {

    private final WebClient webClient;
    private final CepLogRepository cepLogRepository;
    private final ObjectMapper objectMapper;

    public CepService(WebClient webClient, CepLogRepository cepLogRepository, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.cepLogRepository = cepLogRepository;
        this.objectMapper = objectMapper;
    }

    public Optional<Address> buscarCep(String cep) {
        Mono<Optional<Address>> mono = webClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment(cep, "json").build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .map(map -> {
                    // tenta serializar para JSON, com fallback para toString()
                    String bodyStr;
                    try {
                        bodyStr = objectMapper.writeValueAsString(map);
                    } catch (Exception e) {
                        bodyStr = map.toString();
                    }
                    cepLogRepository.save(new CepLog(cep, LocalDateTime.now(), bodyStr));
                    return Optional.ofNullable(Address.fromMap(map));
                })
                .onErrorResume(ex -> {
                    String err = (ex instanceof WebClientResponseException) ? ((WebClientResponseException) ex).getResponseBodyAsString() : ex.getMessage();
                    cepLogRepository.save(new CepLog(cep, LocalDateTime.now(), err != null ? err : "erro ao chamar API externa"));
                    return Mono.just(Optional.empty());
                });

        Optional<Address> result = mono.blockOptional().orElse(Optional.empty());
        return result;
    }
}
