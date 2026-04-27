package com.Workspace.workfree_api.cep.controller;

import com.Workspace.workfree_api.cep.domain.Address;
import com.Workspace.workfree_api.cep.service.CepService;
import com.Workspace.workfree_api.prestadores.exceptionHandler.APIException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> buscarCep(@PathVariable String cep) {
        Optional<Address> opt = cepService.buscarCep(cep);
        return opt.map(address -> ResponseEntity.ok(address))
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "O CEP informado não foi encontrado."));
    }
}
