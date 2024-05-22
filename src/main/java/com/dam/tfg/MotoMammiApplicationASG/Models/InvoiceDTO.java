package com.dam.tfg.MotoMammiApplicationASG.Models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "MM_INVOICE")
public class InvoiceDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Fecha")
    private Date fecha;

    @Column(name = "NombreEmpresa", length = 100)
    private String nombreEmpresa;

    @Column(name = "CifEmpresa", length = 11)
    private String cifEmpresa;

    @Column(name = "DireccionEmpresa", length = 100)
    private String direccionEmpresa;

    @Column(name = "NombreUsuario", length = 50)
    private String nombreUsuario;

    @Column(name = "ApellidosUsuario", length = 50)
    private String apellidosUsuario;

    @Column(name = "DireccionUsuario", length = 100)
    private String direccionUsuario;

    @Column(name = "TipoSeguro", length = 50)
    private String tipoSeguro;

    @Column(name = "TipoVehiculo", length = 50)
    private String tipoVehiculo;

    @Column(name = "FechaRegistro")
    private Date fechaRegistro;

    @Column(name = "FechaFinContrato")
    private Date fechaFinContrato;

    @Column(name = "Coste")
    private double coste;

    @Column(name = "Iva")
    private double iva;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getCifEmpresa() {
        return cifEmpresa;
    }

    public void setCifEmpresa(String cifEmpresa) {
        this.cifEmpresa = cifEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }
}
