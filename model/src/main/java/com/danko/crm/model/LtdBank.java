package com.danko.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ltd_bank")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LtdBank extends BaseEntity {

    @Column(name = "requisites")
    private String requisites;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ltd_id")
    private Ltd ltd;
}
