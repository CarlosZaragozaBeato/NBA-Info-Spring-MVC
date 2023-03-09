package com.carloszaragoza.nbaweb.modelo;

import com.carloszaragoza.nbaweb.modelo.Equipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-08T13:22:39")
@StaticMetamodel(Partido.class)
public class Partido_ { 

    public static volatile SingularAttribute<Partido, String> temporada;
    public static volatile SingularAttribute<Partido, Equipo> idEquipoVisitante;
    public static volatile SingularAttribute<Partido, Long> idPartido;
    public static volatile SingularAttribute<Partido, Short> puntosVisitante;
    public static volatile SingularAttribute<Partido, Equipo> idEquipoLocal;
    public static volatile SingularAttribute<Partido, Short> puntosLocal;

}