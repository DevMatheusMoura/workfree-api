package com.Workspace.workfree_api.prestadores.domain;

import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresRequest;
import com.Workspace.workfree_api.cep.domain.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Prestadores {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id_prestador", updatable = false, unique = true,nullable = false)
    private UUID idPrestadores;
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rua", column = @Column(name = "rua")),
            @AttributeOverride(name = "bairro", column = @Column(name = "bairro")),
            @AttributeOverride(name = "cidade", column = @Column(name = "cidade")),
            @AttributeOverride(name = "uf", column = @Column(name = "uf"))
    })
    private Address endereco;

    private String numero;
    private String complemento;

    private LocalDateTime criadoEm = LocalDateTime.now();


    public Prestadores(PrestadoresRequest prestadoresRequest) {
        this.nomeCompleto = prestadoresRequest.getNomeCompleto();
        this.cpf = prestadoresRequest.getCpf();
        this.funcao = prestadoresRequest.getFuncao();
        this.valorDiaria = prestadoresRequest.getValorDiaria();
        this.telefone = prestadoresRequest.getTelefone();
        this.cep = prestadoresRequest.getCep();
        this.endereco = new Address(prestadoresRequest.getRua(), prestadoresRequest.getBairro(), prestadoresRequest.getCidade(), prestadoresRequest.getUf());
        this.numero = prestadoresRequest.getNumero();
        this.complemento = prestadoresRequest.getComplemento();
    }

    public void atualiza(PrestadoresRequest prestadoresRequest) {
        this.nomeCompleto = prestadoresRequest.getNomeCompleto();
        this.cpf = prestadoresRequest.getCpf();
        this.funcao = prestadoresRequest.getFuncao();
        this.valorDiaria = prestadoresRequest.getValorDiaria();
        this.telefone = prestadoresRequest.getTelefone();
        this.cep = prestadoresRequest.getCep();
        this.endereco = new Address(prestadoresRequest.getRua(), prestadoresRequest.getBairro(), prestadoresRequest.getCidade(), prestadoresRequest.getUf());
        this.numero = prestadoresRequest.getNumero();
        this.complemento = prestadoresRequest.getComplemento();
    }

    // Delegating getters to keep existing DTOs working
    public String getRua() {
        return endereco != null ? endereco.getRua() : null;
    }

    public String getBairro() {
        return endereco != null ? endereco.getBairro() : null;
    }

    public String getCidade() {
        return endereco != null ? endereco.getCidade() : null;
    }

    public String getUf() {
        return endereco != null ? endereco.getUf() : null;
    }
}
