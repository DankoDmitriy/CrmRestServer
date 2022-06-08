package com.danko.crm.service;

import com.danko.crm.model.Status;
import com.danko.crm.service.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<E extends BaseDto> {
    E findById(Long id);

    E save(E e);

    void deleteById(Long id);

    Page<E> findAll(Pageable pageable);

    E update(E e);

    Page<E> findAllByStatus(Pageable pageable, Status status);
}
