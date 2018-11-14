package de.anves.view.controller;

import de.anves.Anhaenger;
import de.anves.AnhaengerTypEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnhaengerSuchen {

    @GetMapping("/AnhaengerSuchen")
    public String anhaengerSuchenForm(Model model) {
        model.addAttribute("anhaengerTypen", AnhaengerTypEnum.class.getEnumConstants());
        return "AnhaengerSuchen";
    }

    @PostMapping("/AnhaengerSuchen")
    public String anhaengerSuchenSubmit(@ModelAttribute List<Anhaenger> anhaengerList) {
        return "VerfuegbareAnhaenger";
    }
}
