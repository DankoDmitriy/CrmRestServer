package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.EmployeeService;
import com.danko.crm.service.dto.EmployeeDto;
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
@RequestMapping(value = "/server/api/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAll(Pageable pageable) {
        Page<EmployeeDto> page = employeeService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    public ResponseEntity<Page<EmployeeDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<EmployeeDto> page = employeeService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Page<EmployeeDto>> getAllByDepartmentIdAndStatus(Pageable pageable,
                                                                           Status status, @PathVariable("id") Long id) {
        Page<EmployeeDto> page = employeeService.findAllByDepartmentIdAndStatus(pageable, id, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> add(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDtoFromDb = employeeService.save(employeeDto);
        return new ResponseEntity<>(employeeDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(
            @Valid @RequestBody EmployeeDto employeeDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.update(employeeDto), HttpStatus.OK);
    }

    @PatchMapping("/updateRoles/{id}")
    public ResponseEntity<EmployeeDto> updateRoles(
            @Valid @RequestBody EmployeeDto employeeDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.updateRoleEmployeeDto(employeeDto), HttpStatus.OK);
    }
}
