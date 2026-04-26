package com.Workspace.workfree_api.prestadores.application.api;

import com.Workspace.workfree_api.prestadores.application.dto.PrestadorDetalhadoResponse;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresListResponse;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresRequest;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresResponse;
import com.Workspace.workfree_api.prestadores.application.service.PrestadoresApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PrestadoresController implements PrestadoresAPI {

    private final PrestadoresApplicationService prestadoresService;

    @Override
    public PrestadoresResponse postPrestadores(PrestadoresRequest prestadoresRequest) {
        log.info("[inicia] PrestadoresController - postPrestadores");
        PrestadoresResponse prestadoresCriado = prestadoresService.criaPrestadores(prestadoresRequest);
        log.info("[finaliza] PrestadoresController - postPrestadores");
        return prestadoresCriado;
    }

    @Override
    public void editaPrestadores(UUID idPrestador, PrestadoresRequest prestadoresRequest) {
        log.info("[inicia] PrestadoresController - editaPrestadores");
        prestadoresService.editaPrestador(idPrestador, prestadoresRequest);
        log.info("[finaliza] PrestadoresController - editaPrestadores");
    }

    @Override
    public List<PrestadoresListResponse> getTodosPrestadores() {
        log.info("[inicia] PrestadoresController - getTodosPrestadores");
        List<PrestadoresListResponse> prestadores = prestadoresService.buscaTodosPrestadores();
        log.info("[finaliza] PrestadoresController - getTodosPrestadores");
        return prestadores;
    }

    @Override
    public PrestadorDetalhadoResponse getPrestador(UUID idPrestador) {
        log.info("[inicia] PrestadoresController - getPrestador");
        PrestadorDetalhadoResponse prestador = prestadoresService.buscaPrestador(idPrestador);
        log.info("[finaliza] PrestadoresController - getPrestador");
        return prestador;
    }

    @Override
    public void deletaPrestadores(UUID idPrestador) {
        log.info("[inicia] PrestadoresController - deletaPrestadores");
        prestadoresService.deletaPrestador(idPrestador);
        log.info("[finaliza] PrestadoresController - deletaPrestadores");
    }
}
