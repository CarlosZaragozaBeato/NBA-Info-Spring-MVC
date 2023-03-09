/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carloszaragoza.nbaweb.controller;

import com.carloszaragoza.nbaweb.dao.EquipoImplementacionDao;
import com.carloszaragoza.nbaweb.modelo.Equipo;
import com.carloszaragoza.nbaweb.repository.EquipoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author carlo
 */
@Controller
public class FrontController {

    @GetMapping(value = "/")
    public String mostrarIndex(Model modelo) {
        return "index";
    }

}
