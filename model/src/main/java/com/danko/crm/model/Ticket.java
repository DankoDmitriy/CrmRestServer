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
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Ticket extends BaseEntity {

    @Column(name = "open_status")
    private Integer openStatus;

    @Column(name = "date_of_receiving")
    private LocalDateTime dateOfReceiving;

    @Column(name = "date_of_finish")
    private LocalDateTime dateOfFinish;

    @Column(name = "date_customers_department_doc")
    private LocalDateTime dateCustomersDepartmentDoc;

    @Column(name = "date_accounting_department_doc")
    private LocalDateTime dateAccountingDepartmentDoc;

    @Column(name = "date_transfer_doc")
    private LocalDateTime dateTransferDoc;

    @Column(name = "system_ticket_id")
    private String systemTicketId;

    @Column(name = "waybill")
    private String waybill;

    @Column(name = "server")
    private Integer server;

    @Column(name = "ups")
    private Integer ups;

    @Column(name = "switch")
    private Integer switchs;

    @Column(name = "workplace")
    private Integer workplace;

    @Column(name = "equipment")
    private Integer equipment;

    @Column(name = "employee_executor_dov")
    private String employeeExecutorDov;

    @Column(name = "job")
    private String job;

    @Column(name = "other")
    private String other;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "auto_id", referencedColumnName = "id")
    private Car car;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ltd_instance_id", referencedColumnName = "id")
    private LtdInstance ltdInstance;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "employee_executor_id", referencedColumnName = "id")
    private Employee employeeExecutor;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "employee_owner_id", referencedColumnName = "id")
    private Employee employeeOwner;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "employee_recipient_id", referencedColumnName = "id")
    private Employee employeeRecipient;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ticket_type_id", referencedColumnName = "id")
    private TicketType ticketType;
}
