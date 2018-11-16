package de.anves.view.controller;

import de.anves.Kunde;
import de.anves.controller.dao.KundeDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
/**
 * Viewcontroller für KundeSuchen und KundeAuswählen
 *  zuerst sollen in KundenSuchen Daten für den richtigen Kunden eingegeben werden,
 *  anschließend wird ein Kunde ausgewählt
 */
public class KundeController {

    KundeDAO dao = new KundeDAO();

    /**
     * Get Mapping für KundeSuchen
     *  fügt das attribut Kunde hinzu, um eingaben später in ihm speichern zu können
     * @param model
     * @return
     */
    @GetMapping("/KundenSuchen")
    public String kundenSuchen(Model model) {
        //kunde
        model.addAttribute("kunde", new Kunde());
        return "KundenSuchen";
    }

    @PostMapping("/KundeAuswaehlen")
    public String kundenAnlegenSubmit(@Valid @ModelAttribute("kunde") Kunde kunde, Model model) {
        //Variable für DB Ergebnisse deklarieren
        List<Kunde> dbergebniss = new ArrayList<>();

        //Unterscheidung, welche Suchkriterien angegeben wurden
        if(kunde.getId() != 0){ //Suche nach ID
            Kunde ergebnis = dao.read(kunde.getId());
            if(ergebnis == null){
                return "redirect:/KundeAnlegen";
            }else{
                dbergebniss.add(ergebnis);
            }

        } else if(kunde.getNachname() != null){ //Suche nach Nachnamen und Geburtsdatum
            //Ergebnissliste aus der Datenbank hinzufügen
            dbergebniss = dao.searchNachname(kunde);

        } else if(kunde.getVorname() != null){  //Suche nach Vornamen und Geburtsdatum
            //Ergebnissliste aus der Datenbank hinzufügen
            dbergebniss = dao.searchVorname(kunde);
        }

        //Wenn Kein Kunde gefunden wurde als nächstes KundeAnlegen aufrufen
        if(dbergebniss == null || dbergebniss.size() == 0){
            return "redirect:/KundeAnlegen";
        }else{  //Wenn Kunden gefunden wurden dem model als attribute hinzufügen um mit themleaf zu verarbeiten
            model.addAttribute("kunden", dbergebniss);
        }

        return "KundeAuswaehlen";
    }
}
