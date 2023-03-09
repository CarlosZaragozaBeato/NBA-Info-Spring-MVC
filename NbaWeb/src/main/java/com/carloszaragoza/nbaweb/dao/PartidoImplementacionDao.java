/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.dao;

import com.carloszaragoza.nbaweb.modelo.Jugador;
import com.carloszaragoza.nbaweb.modelo.Partido;
import com.carloszaragoza.nbaweb.repository.PartidoDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usumaniana
 */
@Repository
public class PartidoImplementacionDao implements PartidoDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean insertarPartido(Partido prt) {

        Session session = sessionFactory.openSession();

        session.getTransaction().begin();
        session.persist(prt);
        session.getTransaction().commit();
        System.out.println("Partido insertado");
        session.close();
        return true;
    }

    @Override
    public boolean eliminarPartido(Long id) {

        boolean exito = false;
        Session sesion = sessionFactory.openSession();
        try {
            sesion.getTransaction().begin();
            Partido prt = sesion.get(Partido.class, id);
            if (prt != null) {
                sesion.remove(prt);
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
    public boolean modificarPartido(Partido prt) {
        Session session = sessionFactory.openSession();

        session.getTransaction().begin();
        session.merge(prt);
        session.getTransaction().commit();
        System.out.println("Partido Modificado");
        session.close();
        return true;
    }

    @Override
    public Partido consultarPartido(Long d) {
        Session session = sessionFactory.openSession();

        String jpql = "from Partido prt WHERE prt.idPartido = :prt";
        Query q = session.createQuery(jpql);
        q.setParameter("prt", d);
        Partido prt = null;
        try {
            prt = (Partido) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return prt;
    }

    @Override
    public List<Partido> obtenerPartido() {
        Session session = sessionFactory.openSession();

        String jpql = "from Partido d";
        Query q = session.createQuery(jpql);
        List<Partido> lista = (List<Partido>) q.getResultList();
        for (Object o : lista) {
            Partido eq = (Partido) o;
        }
        session.close();
        return lista;
    }

}
