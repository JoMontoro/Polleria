/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Controladores;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocationController {

    @GetMapping("/location")
    public String getLocation(Model model) {
        model.addAttribute("address", "Jr victor a Belaunde, 490 Carmen De La Legua Reynoso, Lima 07006");
        model.addAttribute("phone", "+51 946 030 666");
        model.addAttribute("hours", "Lunes a Domingo: 12:00 PM - 9:00 PM");
        return "location";
    }
}
