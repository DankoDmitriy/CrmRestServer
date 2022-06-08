package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.DepartmentService;
import com.danko.crm.service.dto.DepartmentDto;
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
@RequestMapping(value = "/server/api/department", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Page<DepartmentDto>> getAll(Pageable pageable) {
        Page<DepartmentDto> page = departmentService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    public ResponseEntity<Page<DepartmentDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<DepartmentDto> page = departmentService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getById(@PathVariable("id") long id) {
        DepartmentDto departmentDto = departmentService.findById(id);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> add(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto departmentDtoFromDb = departmentService.save(departmentDto);
        return new ResponseEntity<>(departmentDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDto> update(
            @Valid @RequestBody DepartmentDto departmentDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(departmentService.update(departmentDto), HttpStatus.OK);
    }
}
