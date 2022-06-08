package com.danko.crm.rest_server.controller;

import com.danko.crm.model.Status;
import com.danko.crm.service.CarService;
import com.danko.crm.service.dto.CarDto;
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
@RequestMapping(value = "/server/api/car", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CarController {
    private CarService carService;

    @GetMapping
    public ResponseEntity<Page<CarDto>> getAll(Pageable pageable) {
        Page<CarDto> page = carService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/status/")
    public ResponseEntity<Page<CarDto>> getAllByStatus(Pageable pageable, Status status) {
        Page<CarDto> page = carService.findAllByStatus(pageable, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getById(@PathVariable("id") long id) {
        CarDto carDto = carService.findById(id);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarDto> add(@Valid @RequestBody CarDto carDto) {
        CarDto carDtoFromDb = carService.save(carDto);
        return new ResponseEntity<>(carDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarDto> update(
            @Valid @RequestBody CarDto carDto,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(carService.update(carDto), HttpStatus.OK);
    }
}
