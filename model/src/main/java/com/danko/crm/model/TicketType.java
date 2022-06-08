package com.danko.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_type")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TicketType extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "action")
    private Integer action;

    @Column(name = "priority")
    private Integer priority;
}
