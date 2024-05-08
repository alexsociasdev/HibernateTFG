package com.dam.tfg.MotoMammiApplicationASG.Models;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MM_INTERFACE")
public class Interface {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "codExternal", length = 100)
    private String codExternal;

    @Column(name = "codProv", length = 100)
    private String codProv;

    @Column(name = "contJson", columnDefinition = "LONG")
    private String contJson;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "lastUpdate")
    private Timestamp lastUpdate;

    @Column(name = "createdBy", length = 100)
    private String createdBy;

    @Column(name = "updatedBy", length = 100)
    private String updatedBy;

    @Column(name = "codError")
    private int codError;

    @Column(name = "errorMessage", length = 100)
    private String errorMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusProcess", nullable = false)
    private StatusProcess statusProcess;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation", nullable = false)
    private Operation operation;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource", nullable = false)
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "codProv", referencedColumnName = "codprov", insertable = false, updatable = false)
    private Provider provider;

    public Interface() {
    }

    public Interface(int id, String codExternal, String codProv, String contJson, Timestamp creationDate, Timestamp lastUpdate, String createdBy, String updatedBy, int codError, String errorMessage, StatusProcess statusProcess, Operation operation, Resource resource) {
        this.id = id;
        this.codExternal = codExternal;
        this.codProv = codProv;
        this.contJson = contJson;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.codError = codError;
        this.errorMessage = errorMessage;
        this.statusProcess = statusProcess;
        this.operation = operation;
        this.resource = resource;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodExternal() {
        return codExternal;
    }

    public void setCodExternal(String codExternal) {
        this.codExternal = codExternal;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getContJson() {
        return contJson;
    }

    public void setContJson(String contJson) {
        this.contJson = contJson;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getCodError() {
        return codError;
    }

    public void setCodError(int codError) {
        this.codError = codError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public StatusProcess getStatusProcess() {
        return statusProcess;
    }

    public void setStatusProcess(StatusProcess statusProcess) {
        this.statusProcess = statusProcess;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Interface{" +
                "id=" + id +
                ", codExternal='" + codExternal + '\'' +
                ", codProv='" + codProv + '\'' +
                ", contJson='" + contJson + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdate=" + lastUpdate +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", codError=" + codError +
                ", errorMessage='" + errorMessage + '\'' +
                ", statusProcess=" + statusProcess +
                ", operation=" + operation +
                ", resource=" + resource +
                ", provider=" + provider +
                '}';
    }

    // Enumeraciones para los campos ENUM
    public enum StatusProcess {
        N, E, P
    }

    public enum Operation {
        new_op("new"), update("update");

        private final String label;

        Operation(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }

    public enum Resource {
        cus, veh, prt
    }
}
