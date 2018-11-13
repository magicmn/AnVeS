package de.anves.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class KundenAnlegenController {

    @GetMapping("/KundeAnlegen")
    public String kundenAnlegenForm(Model model) {
        //model.addAttribute("KundeAnlegen", new Kunde());
        return "KundeAnlegen";
    }

/*
    @PostMapping("/KundeAnlegen")
    public String kundenAnlegenSubmit(@ModelAttribute Kunde kunde) {
        return "result";
    }*/

}
