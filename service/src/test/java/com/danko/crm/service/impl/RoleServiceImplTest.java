package com.danko.crm.service.impl;

import com.danko.crm.model.Role;
import com.danko.crm.model.Status;
import com.danko.crm.repository.RoleRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.CarDto;
import com.danko.crm.service.dto.RoleDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    private final static String ROLE_NAME = "Role";
    private final static Long ID = 1L;

    private RoleDto expected;
    private Role roleFromDb;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    @BeforeEach
    public void init() {
        LocalDateTime localDateTime = LocalDateTime.now();
        roleFromDb = Role.builder()
                .id(ID)
                .name(ROLE_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = RoleDto.builder()
                .id(ID)
                .name(ROLE_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void findById() {
        Mockito.when(roleRepository.findById(ID)).thenReturn(Optional.of(roleFromDb));
        Mockito.when(entityToDtoConverterService.convert(roleFromDb)).thenReturn(expected);

        RoleDto actual = roleService.findById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void findByIdNegative() {
        Mockito.when(roleRepository.findById(ID))
                .thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> roleService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        Mockito.when(entityToDtoConverterService.convert(roleFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(roleFromDb);
        Mockito.when(roleRepository.save(roleFromDb)).thenReturn(roleFromDb);

        RoleDto actual = roleService.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Role> roles = new PageImpl<>(Arrays.asList(roleFromDb));
        Page<RoleDto> expected = new PageImpl<>(Arrays.asList(this.expected));

        Mockito.when(roleRepository.findAll(pageable)).thenReturn(roles);
        Mockito.when(entityToDtoConverterService.convert(this.roleFromDb)).thenReturn(this.expected);

        Page<RoleDto> actual = roleService.findAll(pageable);

        assertEquals(expected, actual);
    }
}
