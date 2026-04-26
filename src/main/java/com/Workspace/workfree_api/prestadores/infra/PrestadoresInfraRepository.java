package com.Workspace.workfree_api.prestadores.infra;

import com.Workspace.workfree_api.prestadores.domain.Prestadores;
import com.Workspace.workfree_api.prestadores.exceptionHandler.APIException;
import com.Workspace.workfree_api.prestadores.repository.PrestadoresRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PrestadoresInfraRepository implements PrestadoresRepository {

        private final PrestadoresSprintDataJPARepository prestadoresSprintDataJPARepository;


    @Override
    public Prestadores salva(Prestadores prestadores) {
        log.info(" [inicia] PrestadoresInfraRepository - salva");
        prestadoresSprintDataJPARepository.save(prestadores);
        log.info(" [finaliza] PrestadoresInfraRepository - salva");
        return prestadores;
    }

    @Override
    public List<Prestadores> buscaTodosPrestadores() {
        log.info(" [inicia] PrestadoresInfraRepository - buscaTodosPrestadores");
        List<Prestadores> todosPrestadores = prestadoresSprintDataJPARepository.findAll();
        log.info(" [finaliza] PrestadoresInfraRepository - buscaTodosPrestadores");
        return todosPrestadores;
    }

    @Override
    public Prestadores buscaPrestadoresPorId(UUID idPrestadores) {
        log.info(" [inicia] PrestadoresInfraRepository - buscaPrestadoresPorId");
        Prestadores prestadores = prestadoresSprintDataJPARepository.findById(idPrestadores).orElseThrow(()
                -> APIException.build(HttpStatus.NOT_FOUND, "Prestador não encontrado"));
        log.info(" [finaliza] PrestadoresInfraRepository - buscaPrestadoresPorId");
        return prestadores;
    }

    @Override
    public void deleta(Prestadores prestador) {
        log.info(" [inicia] PrestadoresInfraRepository - deleta");
        prestadoresSprintDataJPARepository.delete(prestador);
        log.info(" [finaliza] PrestadoresInfraRepository - deleta");
    }
}
