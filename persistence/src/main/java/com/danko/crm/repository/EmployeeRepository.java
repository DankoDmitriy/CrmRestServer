package com.danko.crm.repository;

import com.danko.crm.model.Employee;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAllByStatus(Pageable pageable, Status status);

    Page<Employee> findAllByDepartment_IdAndStatus(Pageable pageable, Long id, Status status);

    Optional<Employee> findByUserName(String userName);
}
