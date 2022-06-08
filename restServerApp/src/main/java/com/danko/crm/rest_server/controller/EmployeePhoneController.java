package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.EmployeePhoneService;
import com.danko.crm.service.dto.EmployeePhoneDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/server/api/employeePhone", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EmployeePhoneController {
    private EmployeePhoneService employeePhoneService;

    @GetMapping
    public ResponseEntity<Page<EmployeePhoneDto>> getAll(Pageable pageable) {
        Page<EmployeePhoneDto> page = employeePhoneService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    public ResponseEntity<Page<EmployeePhoneDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<EmployeePhoneDto> page = employeePhoneService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeePhoneDto> getById(@PathVariable("id") long id) {
        EmployeePhoneDto employeePhoneDto = employeePhoneService.findById(id);
        return new ResponseEntity<>(employeePhoneDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeePhoneDto> add(@Valid @RequestBody EmployeePhoneDto employeePhoneDto) {
        EmployeePhoneDto employeePhoneDtoFromDb = employeePhoneService.save(employeePhoneDto);
        return new ResponseEntity<>(employeePhoneDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        employeePhoneService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeePhoneDto> update(
            @Valid @RequestBody EmployeePhoneDto employeePhoneDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(employeePhoneService.update(employeePhoneDto), HttpStatus.OK);
    }
}
