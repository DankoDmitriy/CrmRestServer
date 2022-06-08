package com.danko.crm.service.impl;

import com.danko.crm.model.City;
import com.danko.crm.model.Status;
import com.danko.crm.repository.CityRepository;
import com.danko.crm.service.CityService;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.CityDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;

    @Override
    public CityDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private City findEntityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    public CityDto save(CityDto cityDto) {
        Optional<City> optionalCity =cityRepository.findByName(cityDto.getName());

        if (optionalCity.isPresent()){
            return  entityToDtoConverterService.convert(optionalCity.get());
        }else {
            LocalDateTime now = LocalDateTime.now();
            cityDto.setCreated(now);
            cityDto.setUpdate(now);
            cityDto.setStatus(Status.ACTIVE);
            return entityToDtoConverterService.convert(cityRepository.save(dtoToEntityConverterService.convert(cityDto)));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        City city = findEntityById(id);
        city.setStatus(Status.DELETED);
        cityRepository.save(city);
    }

    @Override
    public Page<CityDto> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<CityDto> findAllByStatus(Pageable pageable, Status status) {
        return cityRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public CityDto update(CityDto cityDto) {
        City cityFromDb = findEntityById(cityDto.getId());
        checkCityBeforeUpdate(cityFromDb, cityDto);
        return entityToDtoConverterService.convert(cityRepository.save(cityFromDb));
    }

    private void checkCityBeforeUpdate(City cityFromDb, CityDto cityDto) {
        LocalDateTime now = LocalDateTime.now();

        if (cityDto.getName() != null && !cityDto.getName().equals(cityFromDb.getName())) {
            cityFromDb.setName(cityDto.getName());
        }
        if (cityDto.getStatus() != null && !cityDto.getStatus().equals(cityFromDb.getStatus())) {
            cityFromDb.setStatus(cityDto.getStatus());
        }
        cityFromDb.setUpdate(now);
    }
}
