package com.danko.crm.repository;

import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findAllByDepartment_Id(Long id);

    Page<Position> findAllByStatus(Pageable pageable, Status status);

    Optional<Position> findByName(String name);
}
