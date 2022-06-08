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
import java.time.LocalDateTime;

@Entity
@Table(name = "ltd_contract")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LtdContract extends BaseEntity{

    @Column(name = "contract_start")
    private LocalDateTime contractStart;

    @Column(name = "number")
    private String number;

    @Column(name = "other")
    private String other;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ltd_id")
    private Ltd ltd;
}
