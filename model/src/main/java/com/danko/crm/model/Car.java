package com.danko.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "auto")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Car extends BaseEntity {

    @Column(name = "number")
    private String number;

    @Column(name = "other")
    private String other;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cities_id", referencedColumnName = "id")
    private City city;
}
