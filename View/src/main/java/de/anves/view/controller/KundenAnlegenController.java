package de.anves.view.controller;

import de.anves.Kunde;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class KundenAnlegenController {

    @GetMapping("/KundeAnlegen")
    public void kundenAnlegenForm(Model model) {
        model.addAttribute("Kunde", new Kunde());
     //   return "Kunde";
    }



    @PostMapping("/KundeAnlegen")
    public String kundenAnlegenSubmit(@ModelAttribute Kunde kunde) {
        return "result";
    }

}
