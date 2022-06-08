package com.danko.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ltd_instance")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LtdInstance extends BaseEntity {

    @Column(name = "type")
    private Integer type;

    @Column(name = "distance_main_office")
    private Integer distanceMainOffice;

    @Column(name = "distance_local_office")
    private Integer distanceLocalOffice;

    @Column(name = "others")
    private String others;

    @Column(name = "telecom_cabinet")
    private Integer telecomCabinet;

    @Column(name = "ups")
    private Integer ups;

    @Column(name = "server")
    private Integer server;

    @Column(name = "switch")
    private Integer switchs;

    @Column(name = "workplace")
    private Integer workplace;

    @Column(name = "equipment")
    private Integer equipment;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cities_id", referencedColumnName = "id")
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ltd_id")
    private Ltd ltd;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Employee employee;
}
