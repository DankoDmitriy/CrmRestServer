package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.PhoneTypeService;
import com.danko.crm.service.dto.PhoneTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin()
@RequestMapping(value = "/server/api/phoneType", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PhoneTypeController {
    private PhoneTypeService phoneTypeService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<PhoneTypeDto>> getAll(Pageable pageable) {
        Page<PhoneTypeDto> page = phoneTypeService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<PhoneTypeDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<PhoneTypeDto> page = phoneTypeService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<PhoneTypeDto> getById(@PathVariable("id") long id) {
        PhoneTypeDto phoneTypeDto = phoneTypeService.findById(id);
        return new ResponseEntity<>(phoneTypeDto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<PhoneTypeDto> add(@Valid @RequestBody PhoneTypeDto phoneTypeDto) {
        PhoneTypeDto phoneTypeDtoFromDb = phoneTypeService.save(phoneTypeDto);
        return new ResponseEntity<>(phoneTypeDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        phoneTypeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<PhoneTypeDto> update(
            @Valid @RequestBody PhoneTypeDto phoneTypeDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(phoneTypeService.update(phoneTypeDto), HttpStatus.OK);
    }
}
