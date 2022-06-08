package com.danko.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ltd")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Ltd extends BaseEntity {

    @Column(name = "name_full")
    private String nameFull;

    @Column(name = "name_short")
    private String nameShort;

    @Column(name = "address")
    private String address;

    @Column(name = "unp")
    private String unp;

    @OneToMany(mappedBy = "ltd", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<LtdBank> ltdBanks;

    @OneToMany(mappedBy = "ltd", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<LtdContract> ltdContracts;

    @OneToMany(mappedBy = "ltd", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<LtdInstance> ltdInstances;
}
