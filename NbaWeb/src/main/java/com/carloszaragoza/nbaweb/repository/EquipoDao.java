/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.repository;


import com.carloszaragoza.nbaweb.modelo.Equipo;
import com.carloszaragoza.nbaweb.modelo.Jugador;
import java.util.*;
import org.springframework.stereotype.Repository;
/**
 *
 * @author usumaniana
 */
@Repository
public interface EquipoDao {
        
    public boolean insertarEquipo(Equipo eq);

    public boolean eliminarEquipo(Long id);

    public boolean modificarEquipo(Equipo eq);

    public Equipo consultarEquipo(Long d);

    public List<Equipo> obtenerEquipo();

}
