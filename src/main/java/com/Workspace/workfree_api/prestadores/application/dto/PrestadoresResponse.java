package com.Workspace.workfree_api.prestadores.application.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PrestadoresResponse {

     private UUID idPrestadores;
}
