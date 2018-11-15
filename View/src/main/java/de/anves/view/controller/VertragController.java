package de.anves.view.controller;


import de.anves.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import java.util.ArrayList;
import java.util.Date;


@Controller
public class VertragController {



    /**
     * Vertragsuchen Seite wird aufgebaut
     * @param model
     * @return
     */
    @GetMapping("/VertragSuchen")
    public String getVertragSuchen(Model model) {
            model.addAttribute("suchform",new Suchform());

        return "VertragSuchen";
    }

    /**
     * hier findet die Verarbeitung der VertragSuche statt
     * @param form
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/VertragSuchen")
    public RedirectView postVertragSuchen(@ModelAttribute Suchform form, RedirectAttributes redirectAttributes) {
       //TODO DUMMY entfernen
        ArrayList<Vertrag> vertragsliste = new ArrayList<>();

        redirectAttributes.addFlashAttribute("vertragsliste",vertragsliste);
        return new RedirectView("/VertragAuswaehlen");



    }

    /**
     * Hier wird die VertragAuswaehlen Seite aufgebaut
     * @param flashAttribute Parameter werden aus POST von VertragSuchen bezogen
     * @param model
     * @return
     */
    @GetMapping("/VertragAuswaehlen")
    public String vertragAuswaehlen(@ModelAttribute("flashAttribute") Object flashAttribute, Model model) {
        model.addAttribute("redirectionAttribute", flashAttribute);


        return "VertragAuswaehlen";
    }
    @PostMapping("/VertragAuswaehlen")
    public RedirectView postVertragWaehlen(RedirectAttributes redirectAttributes){
        return new RedirectView("/StatusAendern");
    }

    @PostMapping("/StatusAendern")
    public String getStatusAendern(Model model) {
        model.addAttribute("suchform",new Suchform());

        return "StatusAendern";
    }
    ////////////////////////////////MODELATRIBUTE///////////////////////////////////////////////////////////////////////


    /**
     * Modelattribute für die übertragung der Suchform
     */
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
