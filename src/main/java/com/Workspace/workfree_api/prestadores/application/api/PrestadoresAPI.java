package com.Workspace.workfree_api.prestadores.application.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/prestadores")
public interface PrestadoresAPI {

    @PostMapping
    @ResponseStatus (code = HttpStatus.CREATED)
    PrestadoresResponse postPrestadores(@Value @RequestBody PrestadoresRequest prestadoresRequest);

    @GetMapping
    @ResponseStatus (code = HttpStatus.OK)
    List<PrestadoresListResponse> getTodosPrestadores();

    @PutMapping ("/{idPrestador}/edita-prestador")
    @ResponseStatus (code = HttpStatus.NO_CONTENT)
    void editaPrestadores(@PathVariable UUID idPrestador, @Value @RequestBody PrestadoresRequest prestadoresRequest);

    @GetMapping ("/{idPrestador}")
    @ResponseStatus (code = HttpStatus.OK)
    PrestadorDetalhadoResponse getPrestadores(@PathVariable UUID idPrestador);

    @DeleteMapping ("/{idPrestador}/deleta-prestador")
    @ResponseStatus (code = HttpStatus.NO_CONTENT)
    void deletaPrestadores(@PathVariable UUID idPrestador);
}
