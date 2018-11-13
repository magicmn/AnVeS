package de.anves.view.controller;

import de.anves.Kunde;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class KundenSuchenController {

    @GetMapping("/KundenSuchen")
    public String kundenSuchen(Model model) {
        model.addAttribute("KundenSuchen", new Kunde());
        return "KundenSuchen";
    }

    @RequestMapping(value= "/kundenAnzeigen", method = RequestMethod.POST)
    public String kundenAnzeigen(@ModelAttribute Kunde kunde, BindingResult errors, Model model){
        return "KundenSuchen";
    }
}
