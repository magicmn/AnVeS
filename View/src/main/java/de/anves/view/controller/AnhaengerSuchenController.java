package de.anves.view.controller;

import de.anves.Anhaenger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AnhaengerSuchenController {

    @GetMapping("/AnhaengerSuchen")
    public String AnhaengerSuchen(Model model){
        return "AnhaengerSuchen";
    }

}
