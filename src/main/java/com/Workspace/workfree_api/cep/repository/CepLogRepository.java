package com.Workspace.workfree_api.cep.repository;

import com.Workspace.workfree_api.cep.domain.CepLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepLogRepository extends JpaRepository<CepLog, Long> {
}

