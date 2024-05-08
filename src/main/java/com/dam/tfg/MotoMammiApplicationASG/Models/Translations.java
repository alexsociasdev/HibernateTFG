package com.dam.tfg.MotoMammiApplicationASG.Models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "MM_TRANSLATIONS")
public class Translations {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "codprov", length = 100)
    private String codProv;

    @Column(name = "internalCode", length = 100)
    private String internalCode;

    @Column(name = "externalCode", length = 100)
    private String externalCode;

    @Column(name = "dateIni")
    private Date dateIni;

    @Column(name = "dateEnd")
    private Date dateEnd;

    @ManyToOne
    @JoinColumn(name = "codprov", referencedColumnName = "codprov", insertable = false, updatable = false)
    private Provider provider;

    public Translations() {
    }

    public Translations(int id, String codProv, String internalCode, String externalCode, Date dateIni, Date dateEnd) {
        this.id = id;
        this.codProv = codProv;
        this.internalCode = internalCode;
        this.externalCode = externalCode;
        this.dateIni = dateIni;
        this.dateEnd = dateEnd;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Translations{" +
                "id=" + id +
                ", codProv='" + codProv + '\'' +
                ", internalCode='" + internalCode + '\'' +
                ", externalCode='" + externalCode + '\'' +
                ", dateIni=" + dateIni +
                ", dateEnd=" + dateEnd +
                ", provider=" + provider +
                '}';
    }
}
