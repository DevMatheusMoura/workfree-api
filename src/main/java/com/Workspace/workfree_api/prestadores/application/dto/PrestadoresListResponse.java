package com.Workspace.workfree_api.prestadores.application.dto;

import com.Workspace.workfree_api.prestadores.domain.Prestadores;

import java.util.List;
import java.util.stream.Collectors;

public class PrestadoresListResponse {

    private String nomeCompleto;
    private String funcao;
    private Double valorDiaria;
    private String telefone;

    public static List<PrestadoresListResponse> converte(List<Prestadores> prestadores) {
        return prestadores.stream()
                .map(PrestadoresListResponse::new)
                .collect(Collectors.toList());
    }

    public PrestadoresListResponse(Prestadores prestadores) {
        this.nomeCompleto = prestadores.getNomeCompleto();
        this.funcao = prestadores.getFuncao();
        this.valorDiaria = prestadores.getValorDiaria();
        this.telefone = prestadores.getTelefone();
    }
}
