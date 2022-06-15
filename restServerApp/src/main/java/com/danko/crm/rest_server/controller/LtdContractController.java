package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.LtdContractService;
import com.danko.crm.service.dto.LtdContractDto;
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
@RequestMapping(value = "/server/api/ltd/contract", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LtdContractController {
    private LtdContractService ltdContractService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<LtdContractDto>> getAll(Pageable pageable) {
        Page<LtdContractDto> page = ltdContractService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<LtdContractDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<LtdContractDto> page = ltdContractService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<LtdContractDto> getById(@PathVariable("id") long id) {
        LtdContractDto ltdContractDto = ltdContractService.findById(id);
        return new ResponseEntity<>(ltdContractDto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<LtdContractDto> add(@Valid @RequestBody LtdContractDto ltdContractDto) {
        LtdContractDto ltdContractDtoFromDb = ltdContractService.save(ltdContractDto);
        return new ResponseEntity<>(ltdContractDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        ltdContractService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<LtdContractDto> update(
            @Valid @RequestBody LtdContractDto ltdContractDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(ltdContractService.update(ltdContractDto), HttpStatus.OK);
    }
}
