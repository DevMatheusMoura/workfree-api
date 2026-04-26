package com.Workspace.workfree_api.prestadores.application.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Value
public class PrestadoresRequest {

    @NotBlank(message = "Nome completo é obrigatório")
    private String nomeCompleto;
    @CPF
    private String cpf;
    @NotBlank(message = "Função é obrigatória")
    private String funcao;
    @NotNull(message = "Salário é obrigatório")
    private Double valorDiaria;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    @NotBlank(message = "CEP é obrigatório")
    private String cep;
    @NotBlank(message = "Rua é obrigatório")
    private String rua;
    @NotBlank(message = "Número é obrigatório")
    private String numero;
    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;
    @NotBlank(message = "Cidade é obrigatório")
    private String cidade;
    @NotBlank(message =  "UF é obrigatório")
    private String uf;
    private String complemento;


}
