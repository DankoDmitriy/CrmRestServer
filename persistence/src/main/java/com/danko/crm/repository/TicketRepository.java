package com.danko.crm.repository;

import com.danko.crm.model.Status;
import com.danko.crm.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Page<Ticket> findAllByStatus(Pageable pageable, Status status);

    Page<Ticket> findAllByOpenStatusAndStatus(Pageable pageable, Integer openStatus, Status status);
}
