package de.anves.view.controller;

import de.anves.AnhaengerTyp;
import de.anves.controller.dao.AnhaengerDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AnhaengerController {

    @GetMapping("/AnhaengerSuchen")
    public String getAnhaengerSuchen(Model model) {
        model.addAttribute("anhaengerTypen", AnhaengerTyp.class.getEnumConstants());
        model.addAttribute("anhaengerSuchenForm", new AnhaengerSuchenForm());
        return "AnhaengerSuchen";
    }

    @PostMapping("/VerfuegbareAnhaenger")
    public String submitAnhaengerSuchen(@ModelAttribute AnhaengerSuchenForm anhaengerSuchenForm, Model model) {
        model.addAttribute("anhaengerList", new AnhaengerDAO().readList(new Date(0), new Date(1), null));
        return "VerfuegbareAnhaenger";
    }

    public class AnhaengerSuchenForm {

        private String anhaengerTyp;
        private String zeitraumVon;
        private String zeitraumAb;
        private int tage;

        public String getAnhaengerTyp() {
            return anhaengerTyp;
        }

        public void setAnhaengerTyp(String anhaengerTyp) {
            this.anhaengerTyp = anhaengerTyp;
        }

        public String getZeitraumVon() {
            return zeitraumVon;
        }

        public void setZeitraumVon(String zeitraumVon) {
            this.zeitraumVon = zeitraumVon;
        }

        public String getZeitraumAb() {
            return zeitraumAb;
        }

        public void setZeitraumAb(String zeitraumAb) {
            this.zeitraumAb = zeitraumAb;
        }

        public int getTage() {
            return tage;
        }

        public void setTage(int tage) {
            this.tage = tage;
        }
    }

}
