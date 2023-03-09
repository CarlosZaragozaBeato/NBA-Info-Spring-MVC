/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "PARTIDOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partido.findAll", query = "SELECT p FROM Partido p"),
    @NamedQuery(name = "Partido.findByIdPartido", query = "SELECT p FROM Partido p WHERE p.idPartido = :idPartido"),
    @NamedQuery(name = "Partido.findByTemporada", query = "SELECT p FROM Partido p WHERE p.temporada = :temporada"),
    @NamedQuery(name = "Partido.findByPuntosLocal", query = "SELECT p FROM Partido p WHERE p.puntosLocal = :puntosLocal"),
    @NamedQuery(name = "Partido.findByPuntosVisitante", query = "SELECT p FROM Partido p WHERE p.puntosVisitante = :puntosVisitante")})
public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PARTIDO")
    private Long idPartido;
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "TEMPORADA")
    private String temporada;
    @Column(name = "PUNTOS_LOCAL")
    private Short puntosLocal;
    @Column(name = "PUNTOS_VISITANTE")
    private Short puntosVisitante;
    @JoinColumn(name = "ID_EQUIPO_VISITANTE", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipoVisitante;
    @JoinColumn(name = "ID_EQUIPO_LOCAL", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipoLocal;

    public Partido() {
    }

    public Partido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public Partido(Long idPartido, String temporada) {
        this.idPartido = idPartido;
        this.temporada = temporada;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public Short getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(Short puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public Short getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(Short puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public Equipo getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(Equipo idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

    public Equipo getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(Equipo idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartido != null ? idPartido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partido)) {
            return false;
        }
        Partido other = (Partido) object;
        if ((this.idPartido == null && other.idPartido != null) || (this.idPartido != null && !this.idPartido.equals(other.idPartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloszaragoza.nbaweb.modelo.Partido[ idPartido=" + idPartido + " ]";
    }
    
}
