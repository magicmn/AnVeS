package de.anves.view.controller;


import de.anves.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Date;


@Controller
public class VertragController {


    @GetMapping("/VertragAuswaehlen")
    public String vertragAuswaehlen(@ModelAttribute("flashAttribute") Object flashAttribute, Model model) {
      model.addAttribute("redirectionAttribute", flashAttribute);


        return "VertragAuswaehlen";
    }
    @GetMapping("/VertragSuchen")
    public String getVertragSuchen(Model model) {
            model.addAttribute("suchform",new Suchform());

        return "VertragSuchen";
    }

    @PostMapping("/VertragSuchen")
    public RedirectView postVertragSuchen(@ModelAttribute Suchform form, RedirectAttributes redirectAttributes) {
        ArrayList<Vertrag> vertragsliste = new ArrayList<>();
        Vertrag vertrag = new Vertrag();
        vertrag.setId(123);
        vertrag.setReservierung(new Reservierung());
        vertrag.getReservierung().setKunde(new Kunde());
        vertrag.getReservierung().getKunde().setId(123456789);
        vertrag.getReservierung().getKunde().setNachname("Hans");
        vertrag.getReservierung().getKunde().setVorname("Peter");

        vertrag.setRueckgabe(new Rueckgabe());
        vertrag.setUebergabe(new Uebergabe());
        vertrag.getRueckgabe().setDatum(new Date(100000000));
        vertrag.getUebergabe().setDatum(new Date(100000600));
        vertragsliste.add(vertrag);
        redirectAttributes.addFlashAttribute("vertragsliste",vertragsliste);


        return new RedirectView("/VertragAuswaehlen");



    }
    public class Suchform
    {
        private long vertragID;
        private long kundenID;
        private String von;
        private String bis;

        public String getVon() {
            return von;
        }

        public void setVon(String von) {
            this.von = von;
        }

        public String getBis() {
            return bis;
        }

        public void setBis(String bis) {
            this.bis = bis;
        }



        public long getVertragID() {
            return vertragID;
        }

        public void setVertragID(long vertragID) {
            this.vertragID = vertragID;
        }

        public long getKundenID() {
            return kundenID;
        }

        public void setKundenID(long kundenID) {
            this.kundenID = kundenID;
        }




    }

}
