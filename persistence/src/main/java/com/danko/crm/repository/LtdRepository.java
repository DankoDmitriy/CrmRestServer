package com.danko.crm.repository;

import com.danko.crm.model.Ltd;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LtdRepository extends JpaRepository<Ltd, Long> {
    Page<Ltd> findAllByStatus(Pageable pageable, Status status);

    Optional<Ltd> findByNameFull(String nameFull);
}
