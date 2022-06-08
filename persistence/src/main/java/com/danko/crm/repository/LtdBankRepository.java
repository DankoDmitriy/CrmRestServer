package com.danko.crm.repository;

import com.danko.crm.model.LtdBank;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LtdBankRepository extends JpaRepository<LtdBank, Long> {
    Page<LtdBank> findAllByStatus(Pageable pageable, Status status);
}
