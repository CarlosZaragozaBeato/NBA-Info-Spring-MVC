/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.controller;

import com.carloszaragoza.nbaweb.modelo.Equipo;
import com.carloszaragoza.nbaweb.modelo.Jugador;
import com.carloszaragoza.nbaweb.modelo.Partido;
import com.carloszaragoza.nbaweb.repository.EquipoDao;
import com.carloszaragoza.nbaweb.repository.PartidoDao;
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
@RequestMapping("/partidos")
public class PartidosController {

    @Autowired
    PartidoDao partidoDao;

    @Autowired
    EquipoDao equipoDao;

    ArrayList listaPartidos;
    ArrayList listaEquipos;

    ArrayList<Partido> nuevaLista;
    private final String linkHome = "https://www.balldontlie.io/api/v1/games";

    @GetMapping(value = "/")
    public String mostrarJugadores(Model modelo) {
        listaPartidos = (ArrayList) partidoDao.obtenerPartido();

        ArrayList<Partido> nuevaLista = new ArrayList();

        if (listaPartidos.isEmpty()) {
            nuevaLista = recuperarListaPartido();

            for (Partido prt : nuevaLista) {
                partidoDao.insertarPartido(prt);
            }

            listaPartidos = (ArrayList) partidoDao.obtenerPartido();
        }

        modelo.addAttribute("lstPartidos", listaPartidos);
        return "vistaPartidos";
    }

    @GetMapping(value = "irNuevoPartido")
    public String irNuevoEquipo(Model modelo) {

        Partido partido = new Partido();

        listaEquipos = (ArrayList) equipoDao.obtenerEquipo();

        modelo.addAttribute("lstEquipos", listaEquipos);
        modelo.addAttribute("elPartido", partido);

        return "nuevoPartido";
    }

    @RequestMapping(value = "/nuevoPartido", method = RequestMethod.POST)
    public String nuevoPartido(
            @RequestParam("idPartido") String idPartido,
            @RequestParam("temporada") String temporada,
            @RequestParam("idEquipoLocal") String idLocal,
            @RequestParam("puntosLocal") String puntosLocal,
            @RequestParam("idEquipoVisitante") String idVisitante,
            @RequestParam("puntosVisitante") String puntosVisitante,
            Model modelo) {

        Partido partido = new Partido();

        partido.setIdPartido(Long.parseLong(idPartido));
        partido.setTemporada(temporada);
        partido.setPuntosLocal(Short.parseShort(puntosLocal));
        partido.setPuntosVisitante(Short.parseShort(puntosVisitante));

        Equipo local = equipoDao.consultarEquipo(Long.parseLong(idLocal));
        Equipo visitante = equipoDao.consultarEquipo(Long.parseLong(idVisitante));

        partido.setIdEquipoLocal(local);
        partido.setIdEquipoVisitante(visitante);

        partidoDao.insertarPartido(partido);
        return "redirect:/partidos/";
    }

    @GetMapping(value = "/eliminarPartido")
    public String eliminarPartido(@RequestParam("idPartido") Long idJugador) {
        partidoDao.eliminarPartido(idJugador);
        return "redirect:/partidos/";
    }

    @GetMapping(value = "/irModificarPartido")
    public String modificarPartido(@RequestParam("idPartido") Long codigoPartido, Model modelo) {
        Partido partido = partidoDao.consultarPartido(codigoPartido);

        listaEquipos = (ArrayList) equipoDao.obtenerEquipo();

        modelo.addAttribute("lstEquipos", listaEquipos);

        modelo.addAttribute("elPartido", partido);
        return "modificarPartido";
    }

    @RequestMapping(value = "/modificarPartido", method = RequestMethod.POST)
    public String actualizarPartido(
            @RequestParam("idPartido") String idPartido,
            @RequestParam("temporada") String temporada,
            @RequestParam("idEquipoLocal") String idLocal,
            @RequestParam("puntosLocal") String puntosLocal,
            @RequestParam("idEquipoVisitante") String idVisitante,
            @RequestParam("puntosVisitante") String puntosVisitante,
            Model modelo) {

        Partido partido = new Partido();

        partido.setIdPartido(Long.parseLong(idPartido));
        partido.setTemporada(temporada);
        partido.setPuntosLocal(Short.parseShort(puntosLocal));
        partido.setPuntosVisitante(Short.parseShort(puntosVisitante));

        Equipo local = equipoDao.consultarEquipo(Long.parseLong(idLocal));
        Equipo visitante = equipoDao.consultarEquipo(Long.parseLong(idVisitante));

        partido.setIdEquipoLocal(local);
        partido.setIdEquipoVisitante(visitante);

        partidoDao.modificarPartido(partido);
        return "redirect:/partidos/";
    }

    public String recuperarLista() {

        String URL_API = linkHome;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL_API);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        return response;
    }

    public ArrayList<Partido> recuperarListaPartido() {

        ArrayList<Partido> lista = new ArrayList<>();
        String response = recuperarLista();

        JSONObject json = new JSONObject(response);

        JSONArray array = json.getJSONArray("data");
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = (JSONObject) array.get(i);
            Long id = (long) i;

            Partido partido = new Partido();

            partido.setIdPartido(id);
            partido.setPuntosLocal((short) obj.getInt("home_team_score"));
            partido.setPuntosVisitante((short) obj.getInt("visitor_team_score"));
            partido.setTemporada(obj.getInt("season")+"");
            
            Long localId = (long) obj.getJSONObject("home_team").getInt("id");
            Long visitanteId = (long) obj.getJSONObject("visitor_team").getInt("id");

            partido.setIdEquipoLocal(equipoDao.consultarEquipo(localId));
            partido.setIdEquipoVisitante(equipoDao.consultarEquipo(visitanteId));

            lista.add(partido);
        }

        return lista;
    }
}
