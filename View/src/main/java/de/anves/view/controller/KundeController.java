package de.anves.view.controller;

import de.anves.Kunde;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
/**
 * Viewcontroller für KundeSuchen und KundeAuswählen
 *  zuerst sollen in KundenSuchen Daten für den richtigen Kunden eingegeben werden,
 *  anschließend wird ein Kunde ausgewählt
 */
public class KundeController {

    /**
     * Get Mapping für KundeSuchen
     *  fügt das attribut Kunde hinzu, um eingaben später in ihm speichern zu können
     * @param model
     * @return
     */
    @GetMapping("/KundenSuchen")
    public String kundenSuchen(Model model) {
        model.addAttribute("kunde", new Kunde());
        return "KundenSuchen";
    }

    @PostMapping("/KundenSuchen")
    public void kundenAnlegenSubmit(@Valid @ModelAttribute("kunde") Kunde kunde,
                                    BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("oops");
        }

        System.out.println("nicht oops" +
                " id: " + kunde.getId() +
                " nachname: " + kunde.getNachname() +
                " vorname: " + kunde.getVorname() +
                " gebdat: " + kunde.getGeburtsdatum());
        //return "";
    }
}
