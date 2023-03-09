/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "EQUIPOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByIdEquipo", query = "SELECT e FROM Equipo e WHERE e.idEquipo = :idEquipo"),
    @NamedQuery(name = "Equipo.findByImagen", query = "SELECT e FROM Equipo e WHERE e.imagen = :imagen"),
    @NamedQuery(name = "Equipo.findByNombre", query = "SELECT e FROM Equipo e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Equipo.findByPais", query = "SELECT e FROM Equipo e WHERE e.pais = :pais"),
    @NamedQuery(name = "Equipo.findByConferencia", query = "SELECT e FROM Equipo e WHERE e.conferencia = :conferencia")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_EQUIPO")
    @Basic(optional = false)
    
    private Long idEquipo;
    @Column(name = "IMAGEN")
    private String imagen;


    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PAIS")
    private String pais;


    @Column(name = "CONFERENCIA")
    private String conferencia;
    @OneToMany(mappedBy = "idEquipoVisitante")
    private List<Partido> partidoList;
    @OneToMany(mappedBy = "idEquipoLocal")
    private List<Partido> partidoList1;
    @OneToMany(mappedBy = "idEquipo")
    private List<Jugador> jugadorList;

    public Equipo() {
        partidoList = new ArrayList<>();
        partidoList1 = new ArrayList<>();
        jugadorList = new ArrayList<>();
    }

    public Equipo(Long idEquipo) {
        this.idEquipo = idEquipo;
        partidoList = new ArrayList<>();
        partidoList1 = new ArrayList<>();
        jugadorList = new ArrayList<>();
    }

    public Equipo(Long idEquipo, String imagen, String nombre, String conferencia) {
        this.idEquipo = idEquipo;
        this.imagen = imagen;
        this.nombre = nombre;
        this.conferencia = conferencia;
        partidoList = new ArrayList<>();
        partidoList1 = new ArrayList<>();
        jugadorList = new ArrayList<>();
    }

    public Equipo(String imagen, String nombre, String pais, String conferencia) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.pais = pais;
        this.conferencia = conferencia;
        partidoList = new ArrayList<>();
        partidoList1 = new ArrayList<>();
        jugadorList = new ArrayList<>();

    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    @XmlTransient
    public List<Partido> getPartidoList() {
        return partidoList;
    }

    public void setPartidoList(List<Partido> partidoList) {
        this.partidoList = partidoList;
    }

    @XmlTransient
    public List<Partido> getPartidoList1() {
        return partidoList1;
    }

    public void setPartidoList1(List<Partido> partidoList1) {
        this.partidoList1 = partidoList1;
    }

    @XmlTransient
    public List<Jugador> getJugadorList() {
        return jugadorList;
    }

    public void setJugadorList(List<Jugador> jugadorList) {
        this.jugadorList = jugadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        return true;
    }

}
