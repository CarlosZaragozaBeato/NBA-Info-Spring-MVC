/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.dao;

import com.carloszaragoza.nbaweb.modelo.Equipo;
import com.carloszaragoza.nbaweb.modelo.Jugador;
import com.carloszaragoza.nbaweb.repository.JugadorDao;
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
public class JugadorImplementacionDao implements JugadorDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean insertarJugador(Jugador jg) {
        //EntityManager em = UtilEntityManager.getEm(up);
        Session sesion = sessionFactory.openSession();

        sesion.getTransaction().begin();
        sesion.persist(jg);
        sesion.getTransaction().commit();
        System.out.println("Jugador insertado");
        sesion.close();
        return true;

    }

    @Override
    public boolean eliminarJugador(Long id) {
        boolean exito = false;
        Session sesion = sessionFactory.openSession();
        try {
            sesion.getTransaction().begin();
            Jugador jg = sesion.get(Jugador.class, id);
            if (jg != null) {
                sesion.remove(jg);
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
    public boolean modificarJugador(Jugador jg) {
        Session sesion = sessionFactory.openSession();

        sesion.getTransaction().begin();
        sesion.merge(jg);
        sesion.getTransaction().commit();
        System.out.println("Jguador Modificado");
        sesion.close();
        return true;
    }

    @Override
    public Jugador consultarJugador(Long d) {
        Session sesion = sessionFactory.openSession();

        String jpql = "from Jugador jg WHERE jg.idJugador = :jg";
        Query q = sesion.createQuery(jpql);
        q.setParameter("jg", d);
        Jugador jg = null;
        try {
            jg = (Jugador) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return jg;
    }

    @Override
    public List<Jugador> obtenerJugador() {

        Session sesion = sessionFactory.openSession();

        String jpql = "from Jugador d";
        Query q = sesion.createQuery(jpql);
        List<Jugador> lista = (List<Jugador>) q.getResultList();
        for (Object o : lista) {
            Jugador eq = (Jugador) o;
            System.out.println(eq.getIdEquipo() + "--" + eq.getNombre() + " -- " + eq.getApellido());
        }
        sesion.close();
        return lista;
    }

    @Override
    public List<Jugador> seleccionarJugadoresDeEquipo(Long id) {

        Session session = sessionFactory.openSession();
        String jpql = "from Jugador jrg WHERE jrg.idEquipo.idEquipo = :id";
        Query q = session.createQuery(jpql);
        q.setParameter("id", id);

        List<Jugador> lista = q.getResultList();
        return lista;

    }

    @Override
    public List<Jugador> ordenarAsc() {
        Session sesion = sessionFactory.openSession();

        String jpql = "from Jugador d ORDER BY d.totalPuntos ASC";
        Query q = sesion.createQuery(jpql);
        List<Jugador> lista = (List<Jugador>) q.getResultList();
        for (Object o : lista) {
            Jugador eq = (Jugador) o;
            System.out.println(eq.getIdEquipo() + "--" + eq.getNombre() + " -- " + eq.getApellido());
        }
        sesion.close();
        return lista;

    }

    @Override
    public List<Jugador> ordenarDesc() {

        Session sesion = sessionFactory.openSession();

        String jpql = "from Jugador d ORDER BY d.totalPuntos DESC";
        Query q = sesion.createQuery(jpql);
        List<Jugador> lista = (List<Jugador>) q.getResultList();
        for (Object o : lista) {
            Jugador eq = (Jugador) o;
        }
        sesion.close();
        return lista;
    }
}
