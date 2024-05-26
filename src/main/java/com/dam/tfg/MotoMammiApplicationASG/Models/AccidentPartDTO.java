package com.dam.tfg.MotoMammiApplicationASG.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MM_AccidentPart")
public class AccidentPartDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date accidentDate;

    private String location;
    private String driverName;
    private String driverLicense;
    private String vehicleRegistration;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    @Override
    public String toString() {
        return "AccidentPartDTO{" +
                "description='" + description + '\'' +
                ", accidentDate=" + accidentDate +
                ", location='" + location + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", vehicleRegistration='" + vehicleRegistration + '\'' +
                '}';
    }}
