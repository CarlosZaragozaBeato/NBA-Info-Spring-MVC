/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.controller;

import com.carloszaragoza.nbaweb.modelo.Equipo;
import com.carloszaragoza.nbaweb.modelo.Jugador;
import com.carloszaragoza.nbaweb.repository.EquipoDao;
import com.carloszaragoza.nbaweb.repository.JugadorDao;
import java.util.ArrayList;
import java.util.Random;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author carlo
 */
@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    JugadorDao jugadorDao;

    @Autowired
    EquipoDao equipoDao;

    ArrayList listaJugadores;
    ArrayList listaEquipos;

    ArrayList<Equipo> nuevaLista;
    private final String linkHome = "https://www.balldontlie.io/api/v1/players?per_page=100&page=5";

    @GetMapping(value = "/")
    public String mostrarJugadores(Model modelo) {
        listaJugadores = (ArrayList<Jugador>) jugadorDao.obtenerJugador();
        ArrayList<Jugador> nuevaLista = new ArrayList();

        if (listaJugadores.isEmpty()) {
            nuevaLista = recuperarListaEquipo();

            for (Jugador jgd : nuevaLista) {
                jugadorDao.insertarJugador(jgd);
            }

            listaJugadores = (ArrayList<Jugador>) jugadorDao.obtenerJugador();
        }

        modelo.addAttribute("lstJugador", listaJugadores);
        return "VistaJugadores";
    }

    @GetMapping(value = "/filtrarJugador")
    public String FiltrarJugadores(Model modelo, @RequestParam("orden") String orden) {
        if (orden.equalsIgnoreCase("asc")) {
            listaJugadores = (ArrayList<Jugador>) jugadorDao.ordenarAsc();
        } else {
            listaJugadores = (ArrayList<Jugador>) jugadorDao.ordenarDesc();
        }

        modelo.addAttribute("lstJugador", listaJugadores);
        return "VistaJugadores";
    }

    @GetMapping(value = "irNuevoJugador")
    public String irNuevoEquipo(Model modelo) {

        Jugador jugador = new Jugador();
        listaEquipos = (ArrayList) equipoDao.obtenerEquipo();

        Long id = null;

        modelo.addAttribute("lstEquipos", listaEquipos);
        modelo.addAttribute("elJugador", jugador);
        modelo.addAttribute("idEquipo", id);

        return "nuevoJugador";
    }

    @RequestMapping(value = "/nuevoJugador", method = RequestMethod.POST)
    public String nuevoJugador(@RequestParam("idJugador") String idJugador,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("totalPuntos") String totalPuntos,
            @RequestParam("totalAsistencias") String totalAsistencias,
            @RequestParam("idEquipo") String codigoEquipo,
            Model modelo) {

        Jugador jugador = new Jugador();
        jugador.setIdJugador(Long.parseLong(idJugador));
        jugador.setNombre(nombre);
        jugador.setApellido(apellido);
        jugador.setTotalPuntos(Integer.parseInt(totalPuntos));
        jugador.setTotalAsistencias(Integer.parseInt(totalAsistencias));

        Equipo eq = equipoDao.consultarEquipo(Long.parseLong(codigoEquipo));
        jugador.setIdEquipo(eq);

        jugadorDao.insertarJugador(jugador);
        return "redirect:/jugadores/";
    }

    @GetMapping(value = "/eliminarJugador")
    public String eliminarJugador(@RequestParam("idJugador") Long idJugador) {
        jugadorDao.eliminarJugador(idJugador);
        return "redirect:/jugadores/";
    }

    @GetMapping(value = "/irModificarJugador")
    public String modificarEquipo(@RequestParam("idJugador") Long codigoJugador, Model modelo) {
        Jugador jugador = jugadorDao.consultarJugador(codigoJugador);

        listaEquipos = (ArrayList) equipoDao.obtenerEquipo();

        modelo.addAttribute("lstEquipos", listaEquipos);

        modelo.addAttribute("elJugador", jugador);

        return "modificarJugador";
    }

    @RequestMapping(value = "/modificarJugador", method = RequestMethod.POST)
    public String actualizarEquipo(@RequestParam("idJugador") String idJugador,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("totalPuntos") String totalPuntos,
            @RequestParam("totalAsistencias") String totalAsistencias,
            @RequestParam("idEquipo") String codigoEquipo) {

        Jugador jugador = new Jugador();

        jugador.setIdJugador(Long.parseLong(idJugador));
        jugador.setNombre(nombre);
        jugador.setApellido(apellido);
        jugador.setTotalPuntos(Integer.parseInt(totalPuntos));
        jugador.setTotalAsistencias(Integer.parseInt(totalAsistencias));

        Equipo equipo = equipoDao.consultarEquipo(Long.parseLong(codigoEquipo));
        jugador.setIdEquipo(equipo);

        jugadorDao.modificarJugador(jugador);

        return "redirect:/jugadores/";
    }

    public String recuperarLista() {

        String URL_API = linkHome;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL_API);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        return response;
    }

    public ArrayList<Jugador> recuperarListaEquipo() {

        ArrayList<Jugador> lista = new ArrayList<>();
        String response = recuperarLista();

        JSONObject json = new JSONObject(response);

        JSONArray array = json.getJSONArray("data");
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = (JSONObject) array.get(i);
            Long id = (long) i + 100;

            Random r = new Random();
            int valorDado = r.nextInt(1000) + 1;

            double puntos = 15.5;
            double asistencias = 12.5;

            puntos = puntos * valorDado;
            asistencias = asistencias * valorDado;

            Jugador jugador = new Jugador();

            jugador.setIdJugador(id);
            jugador.setNombre(obj.getString("first_name"));
            jugador.setApellido(obj.getString("last_name"));
            jugador.setTotalAsistencias((int) asistencias);
            jugador.setTotalPuntos((int) puntos);

         
            Long equipoId = (long) obj.getJSONObject("team").getInt("id");

            jugador.setIdEquipo(equipoDao.consultarEquipo(equipoId));

            lista.add(jugador);
        }

        return lista;
    }

}
