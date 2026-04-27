package com.Workspace.workfree_api.cep.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cep_log")
public class CepLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cep;

    @Column(name = "consulta_em", nullable = false)
    private LocalDateTime consultaEm;

    @Lob
    @Column(name = "response_body", nullable = false)
    private String responseBody;

    public CepLog() {
    }

    public CepLog(String cep, LocalDateTime consultaEm, String responseBody) {
        this.cep = cep;
        this.consultaEm = consultaEm;
        this.responseBody = responseBody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDateTime getConsultaEm() {
        return consultaEm;
    }

    public void setConsultaEm(LocalDateTime consultaEm) {
        this.consultaEm = consultaEm;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}

