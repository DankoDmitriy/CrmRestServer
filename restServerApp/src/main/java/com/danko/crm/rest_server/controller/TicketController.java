package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.TicketService;
import com.danko.crm.service.dto.TicketDto;
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
@RequestMapping(value = "/server/api/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TicketController {
    private TicketService ticketService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<TicketDto>> getAll(Pageable pageable, Integer ticketOpenStatus) {
        Page<TicketDto> page = ticketService.findAllByOpenStatus(pageable, ticketOpenStatus);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<TicketDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<TicketDto> page = ticketService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<TicketDto> getById(@PathVariable("id") long id) {
        TicketDto ticketDto = ticketService.findById(id);
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<TicketDto> add(@Valid @RequestBody TicketDto ticketDto) {
        TicketDto ticketDtoFromDb = ticketService.save(ticketDto);
        return new ResponseEntity<>(ticketDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        ticketService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<TicketDto> update(
            @Valid @RequestBody TicketDto ticketDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(ticketService.update(ticketDto), HttpStatus.OK);
    }
}
