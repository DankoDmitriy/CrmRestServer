package com.danko.crm.repository;

import com.danko.crm.model.LtdInstance;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LtdInstanceRepository extends JpaRepository<LtdInstance, Long> {
    Page<LtdInstance> findAllByStatus(Pageable pageable, Status status);

    Page<LtdInstance> findAllByLtd_Id_AndStatus(Pageable pageable, Long id, Status status);
}
