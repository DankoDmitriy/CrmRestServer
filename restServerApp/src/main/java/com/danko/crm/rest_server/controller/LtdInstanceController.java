package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.LtdInstanceService;
import com.danko.crm.service.dto.LtdInstanceDto;
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
@RequestMapping(value = "/server/api/ltd/instance", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LtdInstanceController {
    private LtdInstanceService ltdInstanceService;

    @GetMapping
    public ResponseEntity<Page<LtdInstanceDto>> getAll(Pageable pageable) {
        Page<LtdInstanceDto> page = ltdInstanceService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    public ResponseEntity<Page<LtdInstanceDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<LtdInstanceDto> page = ltdInstanceService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/ltd/")
    public ResponseEntity<Page<LtdInstanceDto>> getAllByLtdId(Pageable pageable, Status status, Long id) {
        Page<LtdInstanceDto> page = ltdInstanceService.findAllByStatusAndLtdId(pageable, status, id);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LtdInstanceDto> getById(@PathVariable("id") long id) {
        LtdInstanceDto ltdInstanceDto = ltdInstanceService.findById(id);
        return new ResponseEntity<>(ltdInstanceDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LtdInstanceDto> add(@Valid @RequestBody LtdInstanceDto ltdInstanceDto) {
        LtdInstanceDto ltdInstanceDtoFromDb = ltdInstanceService.save(ltdInstanceDto);
        return new ResponseEntity<>(ltdInstanceDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        ltdInstanceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LtdInstanceDto> update(
            @Valid @RequestBody LtdInstanceDto ltdInstanceDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(ltdInstanceService.update(ltdInstanceDto), HttpStatus.OK);
    }
}
