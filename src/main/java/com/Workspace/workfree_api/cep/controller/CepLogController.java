package com.Workspace.workfree_api.cep.controller;

import com.Workspace.workfree_api.cep.domain.CepLog;
import com.Workspace.workfree_api.cep.repository.CepLogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cep/logs")
public class CepLogController {

    private final CepLogRepository cepLogRepository;

    public CepLogController(CepLogRepository cepLogRepository) {
        this.cepLogRepository = cepLogRepository;
    }

    @GetMapping
    public List<CepLogResponse> listarLogs() {
        List<CepLog> logs = cepLogRepository.findAll();
        return logs.stream().map(CepLogResponse::from).collect(Collectors.toList());
    }

    public static class CepLogResponse {
        private Long id;
        private String cep;
        private String consultaEm;
        private String responseBody;

        public static CepLogResponse from(CepLog l) {
            CepLogResponse r = new CepLogResponse();
            r.id = l.getId();
            r.cep = l.getCep();
            r.consultaEm = l.getConsultaEm() != null ? l.getConsultaEm().toString() : null;
            r.responseBody = l.getResponseBody();
            return r;
        }

        public Long getId() { return id; }
        public String getCep() { return cep; }
        public String getConsultaEm() { return consultaEm; }
        public String getResponseBody() { return responseBody; }
    }
}

