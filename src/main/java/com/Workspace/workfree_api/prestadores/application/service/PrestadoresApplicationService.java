package com.Workspace.workfree_api.prestadores.application.service;

import com.Workspace.workfree_api.prestadores.application.dto.PrestadorDetalhadoResponse;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresListResponse;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresRequest;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresResponse;
import com.Workspace.workfree_api.prestadores.domain.Prestadores;
import com.Workspace.workfree_api.prestadores.repository.PrestadoresRepository;
import com.Workspace.workfree_api.cep.service.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PrestadoresApplicationService implements PrestadoresService {

    private final PrestadoresRepository prestadoresRepository;
    private final CepService cepService; // ADICIONADO

    @Override
    public PrestadoresResponse criaPrestadores(PrestadoresRequest prestadoresRequest) {
        log.info("[inicia] PrestadoresApplicationService - criaPrestadores");
        PrestadoresRequest atualizada = cepService.buscarCep(prestadoresRequest.getCep())
                .map(address -> prestadoresRequest.withAddress(address))
                .orElse(prestadoresRequest);

        Prestadores prestadores = prestadoresRepository.salva(new Prestadores(atualizada));
        log.info("[finaliza] PrestadoresApplicationService - criaPrestadores");
        return PrestadoresResponse.builder()
                .idPrestadores(prestadores.getIdPrestadores())
                .build();
    }

    @Override
    public List<PrestadoresListResponse> buscaTodosPrestadores() {
        log.info("[inicia] PrestadoresApplicationService - buscaTodosPrestadores");
        List<Prestadores> prestadores = prestadoresRepository.buscaTodosPrestadores();
        log.info("[finaliza] PrestadoresApplicationService - buscaTodosPrestadores");
        return PrestadoresListResponse.converte(prestadores);
    }

    @Override
    public PrestadorDetalhadoResponse buscaPrestador(UUID idPrestadores) {
        log.info("[inicia] PrestadoresApplicationService - buscaPrestadoresPorId");
        Prestadores prestador = prestadoresRepository.buscaPrestadoresPorId(idPrestadores);
        log.info("[finaliza] PrestadoresApplicationService - buscaPrestadoresPorId");
        return new PrestadorDetalhadoResponse(prestador);
    }

    @Override
    public void deletaPrestador(UUID idPrestadores) {
        log.info("[inicia] PrestadoresApplicationService - deletaPrestador");
        Prestadores prestador = prestadoresRepository.buscaPrestadoresPorId(idPrestadores);
        prestadoresRepository.deleta(prestador);
        log.info("[finaliza] PrestadoresApplicationService - deletaPrestador");
    }

    @Override
    public void editaPrestador(UUID idPrestadores, PrestadoresRequest prestadoresRequest) {
        log.info("[inicia] PrestadoresApplicationService - editaPrestador");

        PrestadoresRequest atualizada = cepService.buscarCep(prestadoresRequest.getCep())
                .map(address -> prestadoresRequest.withAddress(address))
                .orElse(prestadoresRequest);

        Prestadores prestador = prestadoresRepository.buscaPrestadoresPorId(idPrestadores);
        prestador.atualiza(atualizada);
        prestadoresRepository.salva(prestador);
        log.info("[finaliza] PrestadoresApplicationService - editaPrestador");
    }

}
