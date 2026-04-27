package com.Workspace.workfree_api.prestadores.application.service;

import com.Workspace.workfree_api.cep.domain.Address;
import com.Workspace.workfree_api.cep.service.CepService;
import com.Workspace.workfree_api.prestadores.application.dto.PrestadoresRequest;
import com.Workspace.workfree_api.prestadores.domain.Prestadores;
import com.Workspace.workfree_api.prestadores.repository.PrestadoresRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PrestadoresApplicationServiceTest {

    @Test
    void criaPrestadores_enriquecidoComEndereco_quandoCepRetornaAddress() {
        PrestadoresRepository repo = mock(PrestadoresRepository.class);
        CepService cepService = mock(CepService.class);

        when(repo.salva(any(Prestadores.class))).thenAnswer(invocation -> invocation.getArgument(0));
        PrestadoresApplicationService service = new PrestadoresApplicationService(repo, cepService);
        PrestadoresRequest request = new PrestadoresRequest(
                "Fulano de Tal",
                "12345678909",
                "Pintor",
                100.0,
                "(11)99999-0000",
                "01001000",
                null,
                "123",
                null,
                null,
                null,
                ""
        );

        when(cepService.buscarCep("01001000")).thenReturn(Optional.of(new Address("Praça da Sé", "Sé", "São Paulo", "SP")));
        service.criaPrestadores(request);
        ArgumentCaptor<Prestadores> captor = ArgumentCaptor.forClass(Prestadores.class);
        verify(repo, times(1)).salva(captor.capture());

        Prestadores saved = captor.getValue();
        assertThat(saved.getRua()).isEqualTo("Praça da Sé");
        assertThat(saved.getBairro()).isEqualTo("Sé");
        assertThat(saved.getCidade()).isEqualTo("São Paulo");
        assertThat(saved.getUf()).isEqualTo("SP");
    }

    @Test
    void criaPrestadores_semEndereco_quandoCepNaoRetornaAddress() {
        PrestadoresRepository repo = mock(PrestadoresRepository.class);
        CepService cepService = mock(CepService.class);
        when(repo.salva(any(Prestadores.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PrestadoresApplicationService service = new PrestadoresApplicationService(repo, cepService);

        PrestadoresRequest request = new PrestadoresRequest(
                "Fulano de Tal",
                "12345678909",
                "Pintor",
                100.0,
                "(11)99999-0000",
                "00000000",
                "Rua Manual",
                "123",
                "Bairro Manual",
                "Cidade Manual",
                "UF",
                ""
        );

        when(cepService.buscarCep("00000000")).thenReturn(Optional.empty());
        service.criaPrestadores(request);
        ArgumentCaptor<Prestadores> captor = ArgumentCaptor.forClass(Prestadores.class);
        verify(repo, times(1)).salva(captor.capture());

        Prestadores saved = captor.getValue();
        assertThat(saved.getRua()).isEqualTo("Rua Manual");
        assertThat(saved.getBairro()).isEqualTo("Bairro Manual");
        assertThat(saved.getCidade()).isEqualTo("Cidade Manual");
        assertThat(saved.getUf()).isEqualTo("UF");
    }
}

