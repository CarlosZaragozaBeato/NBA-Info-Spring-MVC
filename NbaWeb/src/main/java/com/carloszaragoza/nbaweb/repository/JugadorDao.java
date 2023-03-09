/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.repository;


import com.carloszaragoza.nbaweb.modelo.Jugador;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usumaniana
 */
@Repository
public interface JugadorDao {

    public boolean insertarJugador(Jugador jg);

    public boolean eliminarJugador(Long id);

    public boolean modificarJugador(Jugador jg);

    public Jugador consultarJugador(Long d);

    public List<Jugador> obtenerJugador();
    
    public List<Jugador> ordenarAsc();
    
    public List<Jugador> ordenarDesc();
    
    public List<Jugador> seleccionarJugadoresDeEquipo(Long id);
}
