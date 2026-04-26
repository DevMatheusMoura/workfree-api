package com.Workspace.workfree_api.prestadores.repository;

import com.Workspace.workfree_api.prestadores.domain.Prestadores;

import java.util.List;
import java.util.UUID;

public interface PrestadoresRepository {
    Prestadores salva(Prestadores prestadores);
    List<Prestadores> buscaTodosPrestadores();
    Prestadores buscaPrestadoresPorId(UUID idPrestadores);
    void deleta(Prestadores prestador);
}
