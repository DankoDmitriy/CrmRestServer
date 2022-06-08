package com.danko.crm.repository;

import com.danko.crm.model.EmployeePhone;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePhoneRepository extends JpaRepository<EmployeePhone, Long> {
    Page<EmployeePhone> findAllByStatus(Pageable pageable, Status status);
}
