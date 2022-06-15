package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.TicketTypeService;
import com.danko.crm.service.dto.TicketTypeDto;
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
@RequestMapping(value = "/server/api/ticket/type", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TicketTypeController {
    private TicketTypeService ticketTypeService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<TicketTypeDto>> getAll(Pageable pageable) {
        Page<TicketTypeDto> page = ticketTypeService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<TicketTypeDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<TicketTypeDto> page = ticketTypeService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<TicketTypeDto> getById(@PathVariable("id") long id) {
        TicketTypeDto ticketTypeDto = ticketTypeService.findById(id);
        return new ResponseEntity<>(ticketTypeDto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<TicketTypeDto> add(@Valid @RequestBody TicketTypeDto ticketTypeDto) {
        TicketTypeDto ticketTypeDtoFromDb = ticketTypeService.save(ticketTypeDto);
        return new ResponseEntity<>(ticketTypeDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        ticketTypeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<TicketTypeDto> update(
            @Valid @RequestBody TicketTypeDto ticketTypeDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(ticketTypeService.update(ticketTypeDto), HttpStatus.OK);
    }
}
