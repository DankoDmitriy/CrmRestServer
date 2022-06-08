package com.danko.crm.repository;

import com.danko.crm.model.City;
import com.danko.crm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findAllByStatus(Pageable pageable, Status status);

    Optional<City> findByName(String name);
}
