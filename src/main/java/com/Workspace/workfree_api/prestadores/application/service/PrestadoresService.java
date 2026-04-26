package com.Workspace.workfree_api.prestadores.application.service;

import com.Workspace.workfree_api.prestadores.application.dto.PrestadorDetalhadoResponse;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresListResponse;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresRequest;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresResponse;

import java.util.List;
import java.util.UUID;

public interface PrestadoresService {
    PrestadoresResponse criaPrestadores(PrestadoresRequest prestadoresRequest);
    List<PrestadoresListResponse> buscaTodosPrestadores();
    void deletaPrestador(UUID idPrestador);
    PrestadorDetalhadoResponse buscaPrestador(UUID idPrestador);
    void editaPrestador(UUID idPrestador, PrestadoresRequest prestadoresRequest);

}
