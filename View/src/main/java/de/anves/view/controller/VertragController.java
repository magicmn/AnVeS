//package de.anves.view.controller;
//
//
//import de.anves.Kunde;
//import de.anves.Mitarbeiter;
//import de.anves.Reservierung;
//import de.anves.Vertrag;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//
//@Controller
//public class VertragController {
//
//
//    @GetMapping("/VertragAuswaehlen")
//    public String vertragAuswaehlen(@RequestParam(required = false) ArrayList<Vertrag> vertragsliste, Model model) {
//
//
//
//        return "VertragAuswaehlen";
//    }
//    @GetMapping("/VertragSuchen")
//    public String getVertragSuchen(Model model) {
//            model.addAttribute("suchform",new Suchform());
//
//        return "VertragSuchen";
//    }
//
//    @PostMapping("/VertragSuchen")
//    public ModelAndView postVertragSuchen(@ModelAttribute Suchform form, Model model) {
//        ArrayList<Vertrag> vertragsliste = new ArrayList<>();
//        Vertrag vertrag = new Vertrag();
//        vertrag.setId(123);
//        vertrag.setReservierung(new Reservierung());
//        vertrag.getReservierung().setKunde(new Kunde());
//        vertrag.getReservierung().getKunde().setId(123456789);
//        vertrag.getReservierung().getKunde().setNachname("Hans");
//        vertrag.getReservierung().getKunde().setVorname("Peter");
//
//        vertrag.setRueckgabe(new Date(100000000));
//        vertrag.setUebergabe(new Date(100000000));
//        vertrag.setRueckgabeMitarbeiter(new Mitarbeiter());
//        vertragsliste.add(vertrag);
//        model.addAttribute("vertragsliste",vertragsliste);
//
//        return new ModelAndView("redirect:/VertragAuswaehlen","model",model);
//
//
//
//    }
//    public class Suchform
//    {
//        private long vertragID;
//        private long kundenID;
//        private String von;
//        private String bis;
//
//        public String getVon() {
//            return von;
//        }
//
//        public void setVon(String von) {
//            this.von = von;
//        }
//
//        public String getBis() {
//            return bis;
//        }
//
//        public void setBis(String bis) {
//            this.bis = bis;
//        }
//
//
//
//        public long getVertragID() {
//            return vertragID;
//        }
//
//        public void setVertragID(long vertragID) {
//            this.vertragID = vertragID;
//        }
//
//        public long getKundenID() {
//            return kundenID;
//        }
//
//        public void setKundenID(long kundenID) {
//            this.kundenID = kundenID;
//        }
//
//
//
//
//    }
//
//}
