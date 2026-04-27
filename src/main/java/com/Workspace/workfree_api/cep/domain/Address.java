package com.Workspace.workfree_api.cep.domain;

import jakarta.persistence.Embeddable;

import java.util.Map;
import java.util.Objects;

@Embeddable
public class Address {

    private String rua;
    private String bairro;
    private String cidade;
    private String uf;

    public Address() {
    }

    public Address(String rua, String bairro, String cidade, String uf) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public static Address fromMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) return null;
        String rua = safeGet(map, "logradouro");
        String bairro = safeGet(map, "bairro");
        String cidade = safeGet(map, "localidade");
        String uf = safeGet(map, "uf");
        if (rua == null && bairro == null && cidade == null && uf == null) return null;
        return new Address(rua, bairro, cidade, uf);
    }

    private static String safeGet(Map<String, Object> map, String key) {
        Object v = map.get(key);
        return v == null ? null : Objects.toString(v, null);
    }
}
