package com.dam.tfg.MotoMammiApplicationASG.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "MM_VEHICLE")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Dni", length = 10, unique = true)
    private String dni;

    @Column(name = "CarPlate", length = 10, unique = true)
    private String carPlate;

    @Column(name = "VehicleType", length = 50)
    private String vehicleType;

    @Column(name = "Brand", length = 50)
    private String brand;

    @Column(name = "model", length = 50)
    private String model;

    public Vehicle() {
    }

    public Vehicle(int id, String dni, String carPlate, String vehicleType, String brand, String model) {
        this.id = id;
        this.dni = dni;
        this.carPlate = carPlate;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", carPlate='" + carPlate + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
