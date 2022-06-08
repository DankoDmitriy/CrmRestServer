package com.danko.crm.repository;

import com.danko.crm.model.Car;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findAllByStatus(Pageable pageable, Status status);
}
