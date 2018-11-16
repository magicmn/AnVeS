package de.anves.view.controller;

import de.anves.Anhaenger;
import de.anves.AnhaengerTyp;
import de.anves.controller.dao.AnhaengerDAO;
import org.springframework.format.annotation.DateTimeFormat;
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
        System.out.println(anhaengerSuchenForm);
        System.out.println(new AnhaengerDAO().readList(anhaengerSuchenForm.zeitraumVon, anhaengerSuchenForm.zeitraumBis, anhaengerSuchenForm.anhaengerTyp));
        model.addAttribute("anhaengerList", new AnhaengerDAO().readList(anhaengerSuchenForm.zeitraumVon, anhaengerSuchenForm.zeitraumBis, anhaengerSuchenForm.anhaengerTyp));
        model.addAttribute("VerfuegbareAnhaengerForm", new VerfuegbareAnhaengerForm());
        return "VerfuegbareAnhaenger";
    }

    public class AnhaengerSuchenForm {

        private AnhaengerTyp anhaengerTyp;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date zeitraumVon;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date zeitraumBis;

        public AnhaengerTyp getAnhaengerTyp() {
            return anhaengerTyp;
        }

        public void setAnhaengerTyp(AnhaengerTyp anhaengerTyp) {
            this.anhaengerTyp = anhaengerTyp;
        }

        public Date getZeitraumVon() {
            return zeitraumVon;
        }

        public void setZeitraumVon(Date zeitraumVon) {
            this.zeitraumVon = zeitraumVon;
        }

        public Date getZeitraumBis() {
            return zeitraumBis;
        }

        public void setZeitraumBis(Date zeitraumBis) {
            this.zeitraumBis = zeitraumBis;
        }
    }

    public class VerfuegbareAnhaengerForm {
        private Anhaenger anhaenger;

        public Anhaenger getAnhaenger() {
            return anhaenger;
        }

        public void setAnhaenger(Anhaenger anhaenger) {
            this.anhaenger = anhaenger;
        }
    }

}
