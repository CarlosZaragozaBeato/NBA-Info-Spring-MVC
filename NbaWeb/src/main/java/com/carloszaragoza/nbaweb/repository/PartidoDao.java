/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.repository;

import com.carloszaragoza.nbaweb.modelo.Partido;
import java.util.List;
import org.springframework.stereotype.Repository;


/**
 *
 * @author usumaniana
 */
@Repository
public interface PartidoDao {

    public boolean insertarPartido(Partido prt);

    public boolean eliminarPartido(Long id);

    public boolean modificarPartido(Partido prt);

    public Partido consultarPartido(Long d);

    public List<Partido> obtenerPartido();
}
