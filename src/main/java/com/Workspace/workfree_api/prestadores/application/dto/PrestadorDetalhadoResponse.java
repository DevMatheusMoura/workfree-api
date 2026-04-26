package com.Workspace.workfree_api.prestadores.application.dto;

import com.Workspace.workfree_api.prestadores.domain.Prestadores;

import java.util.UUID;

public class PrestadorDetalhadoResponse {

    private UUID idPrestadores;
    private String nomeCompleto;
    private String cpf;
    private String funcao;
    private Double valorDiaria;
    private String telefone;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;

    public PrestadorDetalhadoResponse(Prestadores prestador ) {
        this.idPrestadores = prestador.getIdPrestadores();
        this.nomeCompleto = prestador.getNomeCompleto();
        this.cpf = prestador.getCpf();
        this.funcao = prestador.getFuncao();
        this.valorDiaria = prestador.getValorDiaria();
        this.telefone = prestador.getTelefone();
        this.cep = prestador.getCep();
        this.rua = prestador.getRua();
        this.numero = prestador.getNumero();
        this.bairro = prestador.getBairro();
        this.cidade = prestador.getCidade();
        this.uf = prestador.getUf();
        this.complemento = prestador.getComplemento();
    }

}
