package com.danko.crm.repository;

import com.danko.crm.model.Role;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Page<Role> findAllByStatus(Pageable pageable, Status status);

    Optional<Role> findByName(String name);
}
