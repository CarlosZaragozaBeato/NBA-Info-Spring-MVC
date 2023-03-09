package com.carloszaragoza.nbaweb.modelo;

import com.carloszaragoza.nbaweb.modelo.Jugador;
import com.carloszaragoza.nbaweb.modelo.Partido;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-08T13:22:39")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile ListAttribute<Equipo, Jugador> jugadorList;
    public static volatile SingularAttribute<Equipo, Long> idEquipo;
    public static volatile SingularAttribute<Equipo, String> conferencia;
    public static volatile ListAttribute<Equipo, Partido> partidoList;
    public static volatile SingularAttribute<Equipo, String> imagen;
    public static volatile ListAttribute<Equipo, Partido> partidoList1;
    public static volatile SingularAttribute<Equipo, String> nombre;
    public static volatile SingularAttribute<Equipo, String> pais;

}