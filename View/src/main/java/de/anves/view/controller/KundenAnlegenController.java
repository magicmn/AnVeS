package de.anves.view.controller;

import de.anves.Kunde;
import de.anves.controller.dao.KundeDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class KundenAnlegenController {


    KundeDAO kundeDAO = new KundeDAO();

    @GetMapping("/KundeAnlegen")
    public void kundenAnlegenForm(Model model) {
        model.addAttribute("Kunde", new Kunde());

    }



    @PostMapping("/KundeAnlegen")
    public void kundenAnlegenSubmit(@Valid @ModelAttribute("Kunde")Kunde kunde,
            BindingResult result,  Model model) {
        if (result.hasErrors()) {
            System.out.println("oops");
        }
        //model.addAttribute("geburtsdatum", "testDatumVonJAvaAUS");
        System.out.println(kunde.getId()) ;
        System.out.println(kunde.getVorname());
        System.out.println(kunde.getNachname()) ;
        System.out.println(kunde.getGeburtsdatum());
        System.out.println(kunde.getOrt());
        System.out.println(kunde.getStrasse());
        System.out.println(kunde.getPlz()) ;
        System.out.println(kunde.getHausnummer());


        kundeDAO.create(kunde);
        //return "";
     }

}
