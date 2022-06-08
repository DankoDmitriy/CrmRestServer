package com.danko.crm.repository;

import com.danko.crm.model.LtdContract;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LtdContractRepository extends JpaRepository<LtdContract, Long> {
    Page<LtdContract> findAllByStatus(Pageable pageable, Status status);

    Optional<LtdContract> findByNumber(String number);
}
