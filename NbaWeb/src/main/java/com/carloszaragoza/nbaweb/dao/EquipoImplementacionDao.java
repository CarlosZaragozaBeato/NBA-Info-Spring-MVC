/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.dao;

import com.carloszaragoza.nbaweb.modelo.Equipo;
import com.carloszaragoza.nbaweb.modelo.Jugador;
import com.carloszaragoza.nbaweb.repository.EquipoDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

//OJO IMPORTAR CORRECTAMENTOE EL PAQUETE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author usumaniana
 */
@Repository
public class EquipoImplementacionDao implements EquipoDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean insertarEquipo(Equipo eq) {
        boolean exito = false;
        Session sesion = sessionFactory.openSession();  //Obtener la sesion  


            sesion.getTransaction().begin();
            sesion.save(eq);
            sesion.getTransaction().commit();
            exito = true;

            sesion.getTransaction().rollback();



            sesion.close();
        
        return exito;

    }

    @Override
    public boolean eliminarEquipo(Long id) {

        boolean exito = false;
        Session sesion = sessionFactory.openSession();
        try {
            sesion.getTransaction().begin();
            Equipo eq = sesion.get(Equipo.class, id);
            if (eq != null) {
                sesion.remove(eq);
                exito = true;
            }
            sesion.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            sesion.close();
            return exito;
        }
    }

    @Override
    public boolean modificarEquipo(Equipo eq) {
        boolean exito = false;
        Session sesion = sessionFactory.openSession();  //Obtener la sesion  

        sesion.getTransaction().begin();
        sesion.merge(eq);
        sesion.getTransaction().commit();

        sesion.getTransaction().rollback();

        sesion.close();
        return exito;

    }

    @Override
    public Equipo consultarEquipo(Long d) {

        Session sesion = sessionFactory.openSession();   //Obtener la sesion  
        Equipo eq = sesion.get(Equipo.class, d); //equivalente a find()
        sesion.close();

        return eq;
    }

    @Override
    @Transactional()
    public List<Equipo> obtenerEquipo() {

        //EntityManager em = UtilEntityManager.getEm(up);
        Session session = sessionFactory.openSession();
        org.hibernate.query.Query q = session.createQuery("from Equipo e");
        List<Equipo> lista = q.getResultList();
        session.close();
        return lista;

    }



}
