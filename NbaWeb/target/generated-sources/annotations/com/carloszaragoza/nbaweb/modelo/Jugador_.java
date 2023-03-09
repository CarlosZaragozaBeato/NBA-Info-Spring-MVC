package com.carloszaragoza.nbaweb.modelo;

import com.carloszaragoza.nbaweb.modelo.Equipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-08T13:22:39")
@StaticMetamodel(Jugador.class)
public class Jugador_ { 

    public static volatile SingularAttribute<Jugador, Equipo> idEquipo;
    public static volatile SingularAttribute<Jugador, Long> idJugador;
    public static volatile SingularAttribute<Jugador, Integer> totalPuntos;
    public static volatile SingularAttribute<Jugador, Integer> totalAsistencias;
    public static volatile SingularAttribute<Jugador, String> apellido;
    public static volatile SingularAttribute<Jugador, String> nombre;

}