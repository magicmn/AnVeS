package de.anves.view.controller;


import de.anves.Vertrag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class VertragController {


    @GetMapping("/VertragAuswaehlen")
    public String vertragAuswaehlen(@RequestParam(required = false) ArrayList<Vertrag> vertragsliste, Model model) {
        model.addAttribute("vertragsliste", vertragsliste);

        return "VertragAuswaehlen";
    }
    @GetMapping("/VertragSuchen")
    public String vertragSuchen( Model model) {

        return "VertragSuchen";
    }
}
