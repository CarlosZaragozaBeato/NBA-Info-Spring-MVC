/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.controller;

import com.carloszaragoza.nbaweb.dao.EquipoImplementacionDao;
import com.carloszaragoza.nbaweb.modelo.Equipo;
import com.carloszaragoza.nbaweb.modelo.Jugador;
import com.carloszaragoza.nbaweb.repository.EquipoDao;
import com.carloszaragoza.nbaweb.repository.JugadorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usumaniana
 */
@Controller
@RequestMapping("/equipos")
public class EquiposController {

    @Autowired
    EquipoDao equipoDao;

    @Autowired
    JugadorDao jugadorDao;

    ArrayList listaEquipos;

    ArrayList<Equipo> nuevaLista;
    private final String linkHome = "https://www.balldontlie.io/api/v1/teams";

    @GetMapping(value = "/")
    public String mostrarEquipos(Model modelo) {
        listaEquipos = (ArrayList<Equipo>) equipoDao.obtenerEquipo();

        if (listaEquipos.isEmpty()) {

            nuevaLista = recuperarListaEquipo();

            for (Equipo eq : nuevaLista) {
                equipoDao.insertarEquipo(eq);
            }

            listaEquipos = (ArrayList<Equipo>) equipoDao.obtenerEquipo();
        }

        modelo.addAttribute("lstEquipos", listaEquipos);
        return "vistaEquipos";
    }

    @GetMapping(value = "/filtrarEquipo")
    public String mostrarEquiposFiltrados(@RequestParam("nombreFiltrado") String nombreFiltrado,
            Model modelo) {

        ArrayList<Equipo> listaFiltrada = new ArrayList();
        for (Equipo eq : equipoDao.obtenerEquipo()) {
            if (eq.getNombre().toUpperCase().contains(nombreFiltrado.toUpperCase())) {
                listaFiltrada.add(eq);
            }
        }
        modelo.addAttribute("lstEquipos", listaFiltrada);
        return "vistaEquipos";
    }

    @GetMapping(value = "irNuevoEquipo")
    public String irNuevoEquipo(Model modelo) {

        modelo.addAttribute("lstEquipos", listaEquipos);

        Equipo equipo = new Equipo();

        modelo.addAttribute("elEquipo", equipo);

        return "nuevoEquipo";
    }

    @PostMapping(value = "nuevoEquipo")
    public String nuevoEquipo(@ModelAttribute("elEquipo") Equipo equipo, Model modelo) {

        equipoDao.insertarEquipo(equipo);
        return "redirect:/equipos/";
    }

    @GetMapping(value = "/irModificarEquipo")
    public String modificarEquipo(@RequestParam("idEquipo") Long codigoEquipo, Model modelo) {
        Equipo equipo = equipoDao.consultarEquipo(codigoEquipo);

        modelo.addAttribute("lstEquipos", listaEquipos);

        modelo.addAttribute("elEquipo", equipo);

        return "modificarEquipo";
    }

    @PostMapping(value = "/modificarEquipo")
    public String actualizarEquipo(@ModelAttribute("equipoMod") Equipo equipo, @RequestParam("idEquipo") Long codigoEquipo) {

        equipoDao.modificarEquipo(equipo);
        return "redirect:/equipos/";
    }

    @GetMapping(value = "/eliminarEquipo")
    public String eliminarCliente(@RequestParam("idEquipo") Long idEquipo) {
        equipoDao.eliminarEquipo(idEquipo);

        return "redirect:/equipos/";
    }

    @GetMapping(value = "irJugadoresEquipo")
    public String irJugadoresEquipo(@RequestParam("idEquipo") Long codigoEquipo, Model modelo) {
        Equipo equipo = equipoDao.consultarEquipo(codigoEquipo);

        ArrayList listaJugadores = (ArrayList) jugadorDao.seleccionarJugadoresDeEquipo(codigoEquipo);
        modelo.addAttribute("lstJugadores", listaJugadores);

        modelo.addAttribute("elEquipo", equipo);

        return "JugadoresEquipo";
    }

    public String recuperarLista() {

        String URL_API = linkHome;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL_API);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        return response;
    }

    public ArrayList<Equipo> recuperarListaEquipo() {

        ArrayList<Equipo> lista = new ArrayList<>();
        String response = recuperarLista();

        JSONObject json = new JSONObject(response);

        JSONArray array = json.getJSONArray("data");
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = (JSONObject) array.get(i);
            
            
            
            Long id = (long) i+1;
            Equipo equipo = new Equipo();

            equipo.setIdEquipo(id);
            equipo.setNombre(obj.getString("full_name"));
            equipo.setConferencia(obj.getString("division"));
            equipo.setPais(obj.getString("city"));

            String[] imagenes = {
                "https://i.postimg.cc/V6SW-BFcD/1200px-Los-Angeles-Lakers-logo-svg.png",
                "https://i.postimg.cc/j2P6Bd2f/Golden-State-Warriors-logo-svg.png",
                "https://i.postimg.cc/63DLHGWw/Miami-Heat-logo-svg.png",
                "https://i.postimg.cc/d34ZXcTG/celtics.png",
                "https://i.postimg.cc/1t0qGqmC/spurs.png",
                "https://i.postimg.cc/zG9b4j32/logo-Denver-Nuggets.png",
                "https://i.postimg.cc/PJt825Nt/Dallas-Mavericks-logo-svg.png",
                "https://i.postimg.cc/SN6KvjPx/atlanta-hawks-basketball-club.png",
                "https://i.postimg.cc/fbLTyCRV/brooklyn-nets.png",
                "https://i.postimg.cc/GLF57yPf/charlotte-hornets-2.png",
                "https://i.postimg.cc/GpLnzx8b/chicago-bulls-1.png",
                "https://i.postimg.cc/8Ds61HWT/cleveland-cavaliers.png",
                "https://i.postimg.cc/7Y0CrK5D/detroit-pistons.png",
                "https://i.postimg.cc/1RLgdk5J/houston-rockets-1.png",
                "https://i.postimg.cc/9Q2Dmy99/indiana-pacers.png",
                "https://i.postimg.cc/bJZJCT77/la-clippers.png",
                "https://i.postimg.cc/MptpzNqk/memphis-grizzlies-1.png",
                "https://i.postimg.cc/MZRZnvqm/milwaukee-bucks.png",
                "https://i.postimg.cc/pXJ2B499/minnesota-timberwolves-1.png",
                "https://i.postimg.cc/TY6dgfPM/new-orleans-pelicans.png",
                "https://i.postimg.cc/13Ry7B6v/new-york-knicks-1.png",
                "https://i.postimg.cc/vTLd3xmR/orlando-magic.png",
                "https://i.postimg.cc/JzdC6LRh/philidephia-76ers.png",
                "https://i.postimg.cc/dtwFD6WJ/phoenix-suns-2.png",
                "https://i.postimg.cc/rwpLNmXb/portland-trail-blazers.png",
                "https://i.postimg.cc/prBgDN0R/sacramento-kings-2.png",
                "https://i.postimg.cc/PJkjWQTz/seattle-supersonics.png",
                "https://i.postimg.cc/5yJhY9Ss/toronto-raptors-3.png",
                "https://i.postimg.cc/CLhyw111/utah-jazz-1.png",
                "https://i.postimg.cc/7ZRj1nNG/washington-wizards-3.png"};

            switch (obj.getString("name")) {

                case "Lakers":
                    equipo.setImagen(imagenes[0]);
                    break;

                case "Warriors":
                    equipo.setImagen(imagenes[1]);
                    break;
                case "Heat":
                    equipo.setImagen(imagenes[2]);
                    break;
                case "Celtics":
                    equipo.setImagen(imagenes[3]);
                    break;
                case "Spurs":
                    equipo.setImagen(imagenes[4]);
                    break;
                case "Nuggets":
                    equipo.setImagen(imagenes[5]);
                    break;
                case "Mavericks":
                    equipo.setImagen(imagenes[6]);
                    break;
                case "Hawks":
                    equipo.setImagen(imagenes[7]);
                    break;
                case "Nets":
                    equipo.setImagen(imagenes[8]);
                    break;
                case "Hornets":
                    equipo.setImagen(imagenes[9]);
                    break;
                case "Bulls":
                    equipo.setImagen(imagenes[10]);
                    break;
                case "Cavaliers":
                    equipo.setImagen(imagenes[11]);
                    break;
                case "Pistons":
                    equipo.setImagen(imagenes[12]);
                    break;
                case "Rockets":
                    equipo.setImagen(imagenes[13]);
                    break;
                case "Pacers":
                    equipo.setImagen(imagenes[14]);
                    break;
                case "Clippers":
                    equipo.setImagen(imagenes[15]);
                    break;
                case "Grizzlies":
                    equipo.setImagen(imagenes[16]);
                    break;
                case "Bucks":
                    equipo.setImagen(imagenes[17]);
                    break;
                case "Timberwolves":
                    equipo.setImagen(imagenes[18]);
                    break;
                case "Pelicans":
                    equipo.setImagen(imagenes[19]);
                    break;
                case "Knicks":
                    equipo.setImagen(imagenes[20]);
                    break;
                case "Thunder":
                    equipo.setImagen(imagenes[26]);
                    break;
                case "Magic":
                    equipo.setImagen(imagenes[21]);
                    break;
                case "76ers":
                    equipo.setImagen(imagenes[22]);
                    break;
                case "Suns":
                    equipo.setImagen(imagenes[23]);
                    break;
                case "Trail Blazers":
                    equipo.setImagen(imagenes[24]);
                    break;
                case "Kings":
                    equipo.setImagen(imagenes[25]);
                    break;
                case "Raptors":
                    equipo.setImagen(imagenes[27]);
                    break;
                case "Jazz":
                    equipo.setImagen(imagenes[28]);
                    break;
                case "Wizards":
                    equipo.setImagen(imagenes[29]);
                    break;

            }

            lista.add(equipo);
        }

        return lista;
    }

}
