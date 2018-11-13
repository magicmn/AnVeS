package de.anves.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KundenAnlegenController {

    @GetMapping("/")
    public String kundenAnlegen(Model model) {
        /*model.addAttribute("KundenAnlegen", new Kunde());*/
        return "kundeAnlegen";
    }

}
