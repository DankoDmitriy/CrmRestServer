package com.danko.crm.repository;

import com.danko.crm.model.PhoneType;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneTypeRepository extends JpaRepository<PhoneType, Long> {
    Page<PhoneType> findAllByStatus(Pageable pageable, Status status);

    Optional<PhoneType> findByName(String name);
}
