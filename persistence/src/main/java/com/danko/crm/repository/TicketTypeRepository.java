package com.danko.crm.repository;

import com.danko.crm.model.Status;
import com.danko.crm.model.TicketType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    Page<TicketType> findAllByStatus(Pageable pageable, Status status);

    Optional<TicketType> findByName(String name);
}
