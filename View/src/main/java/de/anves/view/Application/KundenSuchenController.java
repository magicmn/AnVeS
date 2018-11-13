package de.anves.view.Application;

import de.anves.Kunde;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
