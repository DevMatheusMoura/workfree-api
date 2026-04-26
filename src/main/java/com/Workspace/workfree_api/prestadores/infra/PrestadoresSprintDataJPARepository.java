package com.Workspace.workfree_api.prestadores.infra;

import com.Workspace.workfree_api.prestadores.domain.Prestadores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrestadoresSprintDataJPARepository extends JpaRepository<Prestadores, UUID> {
}
