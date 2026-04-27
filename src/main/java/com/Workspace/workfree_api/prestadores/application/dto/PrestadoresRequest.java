package com.Workspace.workfree_api.prestadores.application.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;
import com.Workspace.workfree_api.cep.domain.Address;

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
    private String rua; // removido NotBlank para permitir enriquecimento
    @NotBlank(message = "Número é obrigatório")
    private String numero;
    private String bairro; // removido NotBlank
    private String cidade; // removido NotBlank
    private String uf; // removido NotBlank
    private String complemento;

    // Método utilitário que cria uma nova instância substituindo apenas os campos de endereço
    public PrestadoresRequest withAddress(Address address) {
        if (address == null) {
            return this;
        }

        String newRua = address.getRua() != null ? address.getRua() : this.rua;
        String newBairro = address.getBairro() != null ? address.getBairro() : this.bairro;
        String newCidade = address.getCidade() != null ? address.getCidade() : this.cidade;
        String newUf = address.getUf() != null ? address.getUf() : this.uf;

        return new PrestadoresRequest(
                this.nomeCompleto,
                this.cpf,
                this.funcao,
                this.valorDiaria,
                this.telefone,
                this.cep,
                newRua,
                this.numero,
                newBairro,
                newCidade,
                newUf,
                this.complemento
        );
    }

}
