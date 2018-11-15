package de.anves.view.controller;

import de.anves.Anhaenger;
import de.anves.AnhaengerTyp;
import de.anves.Schadensbericht;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller
@RequestMapping(value = "/VerfuegbareAnhaenger")
public class VerfuegbareAnhaenger {

    private static List<Anhaenger> anhaengerListe = new ArrayList<Anhaenger>();
    private static List<Schadensbericht> schadensberichtListe = new ArrayList<Schadensbericht>();
    ClientController client = new ClientController();
    DateFormatter df = new DateFormatter();

    @GetMapping
    public String getVerfuegbareAnhaenger(@RequestParam(required = true) ArrayList<Anhaenger> anhaengerListe, Model model){
        Date date = df.stringToDate("12.12.2012 11:00");

        Schadensbericht schadensbericht = new Schadensbericht();
        schadensbericht.setAnmerkungKunde("Alles tutti");
        schadensbericht.setBeschreibung("Nix");
        schadensbericht.setDatum(date);
        schadensbericht.setId(1);
        schadensberichtListe.add(schadensbericht);

        Anhaenger anhaenger = new Anhaenger();
        anhaenger.setAnhaengerTyp(AnhaengerTyp.EINACHSER);
        anhaenger.setId(1);
        anhaenger.setKennzeichen("K-ZZ 1234");
        anhaenger.setNaechsteHU(date);
        anhaenger.setSchadensBerichte(schadensberichtListe);
        anhaengerListe.add(anhaenger);


        model.addAttribute(anhaengerListe);
        return null;
    }

}
