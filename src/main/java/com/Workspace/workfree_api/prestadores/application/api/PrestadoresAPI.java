package com.Workspace.workfree_api.prestadores.application.api;

import com.Workspace.workfree_api.prestadores.application.dto.PrestadorDetalhadoResponse;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresListResponse;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresRequest;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/prestadores")
public interface PrestadoresAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    PrestadoresResponse postPrestadores(@Valid @RequestBody PrestadoresRequest prestadoresRequest);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<PrestadoresListResponse> getTodosPrestadores();

    @PutMapping("/{idPrestador}/edita-prestador")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void editaPrestadores(@PathVariable UUID idPrestador, @RequestBody @Valid PrestadoresRequest prestadoresRequest);

    @GetMapping("/{idPrestador}")
    @ResponseStatus(code = HttpStatus.OK)
    PrestadorDetalhadoResponse getPrestador(@PathVariable UUID idPrestador);

    @DeleteMapping("/{idPrestador}/deleta-prestador")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaPrestadores(@PathVariable UUID idPrestador);
}
