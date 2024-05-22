package com.dam.tfg.MotoMammiApplicationASG.Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "MM_CUSTOMER")
public class CustomerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "BirthDate")
    private Date birthDate;

    @Column(name = "PostalCode", length = 50)
    private String postalCode;

    @Column(name = "StreetType", length = 50)
    private String streetType;

    @Column(name = "City", length = 50)
    private String city;

    @Column(name = "NumberStreet")
    private int numberStreet;

    @Column(name = "PhoneNumber", length = 50)
    private String phoneNumber;

    @Column(name = "Dni", length = 10, nullable = false, unique = true)
    private String dni;

    @Column(name = "LicenceType", length = 10)
    private String licenceType;

    @Column(name = "Email", length = 50)
    private String email;

    @Column(name = "Gender", length = 50)
    private String gender;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleDTO> vehicles;

    public CustomerDTO() {
    }

    public CustomerDTO(int id, String name, String firstName, String lastName, Date birthDate, String postalCode, String streetType, String city, int numberStreet, String phoneNumber, String dni, String licenceType, String email, String gender) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.postalCode = postalCode;
        this.streetType = streetType;
        this.city = city;
        this.numberStreet = numberStreet;
        this.phoneNumber = phoneNumber;
        this.dni = dni;
        this.licenceType = licenceType;
        this.email = email;
        this.gender = gender;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumberStreet() {
        return numberStreet;
    }

    public void setNumberStreet(int numberStreet) {
        this.numberStreet = numberStreet;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", postalCode='" + postalCode + '\'' +
                ", streetType='" + streetType + '\'' +
                ", city='" + city + '\'' +
                ", numberStreet=" + numberStreet +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dni='" + dni + '\'' +
                ", licenceType='" + licenceType + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
