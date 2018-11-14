package de.anves.view.controller;


import de.anves.Kunde;
import de.anves.Mitarbeiter;
import de.anves.Reservierung;
import de.anves.Vertrag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;


@Controller
public class VertragController {


    @GetMapping("/VertragAuswaehlen")
    public String vertragAuswaehlen(@RequestParam(required = false) ArrayList<Vertrag> vertragsliste, Model model) {
        vertragsliste = new ArrayList<>();
        Vertrag vertrag = new Vertrag();
        vertrag.setId(123);
        vertrag.setReservierung(new Reservierung());
        vertrag.getReservierung().setKunde(new Kunde());
        vertrag.getReservierung().getKunde().setId(123456789);
        vertrag.getReservierung().getKunde().setNachname("Hans");
        vertrag.getReservierung().getKunde().setVorname("Peter");

        vertrag.setRueckgabe(new Date(100000000));
        vertrag.setUebergabe(new Date(100000000));
        vertrag.setRueckgabeMitarbeiter(new Mitarbeiter());
        vertragsliste.add(vertrag);
        model.addAttribute("vertragsliste", vertragsliste);

        return "VertragAuswaehlen";
    }
    @GetMapping("/VertragSuchen")
    public String vertragSuchen( Model model) {

        return "VertragSuchen";
    }
}
