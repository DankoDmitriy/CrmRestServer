package com.danko.crm.repository;

import com.danko.crm.model.Department;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Page<Department> findAllByStatus(Pageable pageable, Status status);

    Optional<Department> findByName(String name);
}
