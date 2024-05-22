package com.dam.tfg.MotoMammiApplicationASG.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "MM_PROVIDERS")
public class ProviderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "codprov", unique = true, nullable = false)
    private String codprov;

    @Column(name = "name")
    private String name;

    @Column(name = "dateIni")
    private Date dateIni;

    @Column(name = "dateEnd")
    private Date dateEnd;

    @Column(name = "SwiAct")
    private boolean swiAct;

    public ProviderDTO() {
    }

    public ProviderDTO(int id, String codprov, String name, Date dateIni, Date dateEnd, boolean swiAct) {
        this.id = id;
        this.codprov = codprov;
        this.name = name;
        this.dateIni = dateIni;
        this.dateEnd = dateEnd;
        this.swiAct = swiAct;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodprov() {
        return codprov;
    }

    public void setCodprov(String codprov) {
        this.codprov = codprov;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateIni() {
        return dateIni;
    }

    public void setDateIni(Date dateIni) {
        this.dateIni = dateIni;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isSwiAct() {
        return swiAct;
    }

    public void setSwiAct(boolean swiAct) {
        this.swiAct = swiAct;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", codprov='" + codprov + '\'' +
                ", name='" + name + '\'' +
                ", dateIni=" + dateIni +
                ", dateEnd=" + dateEnd +
                ", swiAct=" + swiAct +
                '}';
    }
}
