// Javascript für KundenSuchen

//globale Variablen für Inputfelder setzten, um nicht jedes Mal über das DOM auf die Elemente zugreifen zu müssen
var kundennummer = document.getElementById("kundennummer");
var nachname = document.getElementById("nachname");
var vorname = document.getElementById("vorname");
var geburtsdatum = document.getElementById("geburtsdatum");

//EventListener für kundennummer hinzufügen
kundennummer.addEventListener("focus", kundenNummerOnFocus);
//kundennummer.addEventListener("change", kundenNummerOnChange);    TODO
kundennummer.addEventListener("focusout", kundenNummerOnFocusOut);

//EventListener für nachname hinzufügen
nachname.addEventListener("focus", nachnameOnFocus);
nachname.addEventListener("focusout", nachnameOnFocusOut);

//EventListener für vorname hinzufügen
vorname.addEventListener("focus", vornameOnFocus);
vorname.addEventListener("focusout", vornameOnFocusOut);

//EventListener für kundennummer hinzufügen
geburtsdatum.addEventListener("focus", geburtsdatumOnFocus);
//geburtsdatum.addEventListener("change", geburtsdatumOnChange);    TODO
geburtsdatum.addEventListener("focusout", geburtsdatumOnFocusOut);

/*
* EventListener für Kundennummer OnFocus
* Graut Felder aus, die nicht mit einer Eingabe in diesem feld kombinierbar sind
* Wenn Kundennummer eingegeben wurde sind andere Felder disabled
*/
function kundenNummerOnFocus(){
    //Wenn die anderen Felder leer sind deaktivieren
    if(nachname.value == "" && vorname.value == "" && geburtsdatum.value == ""){
        nachname.setAttribute('disabled', 'true');
        vorname.setAttribute('disabled', 'true');
        geburtsdatum.setAttribute('disabled', 'true');
    } else  //Feld kundennummer ist nicht erlaubt, hätte eigentlich nicht aktiviert sein dürfen
    {
        kundennummer.setAttribute('disabled', 'true');
    }
}

/*
* EventListener für Kundennummer OnFocusOut
* Wenn ein Wert eingegeben wurde soll überprüft werden, ob eine Nummer eingegeben wurde.
*   Sonst wird ein Fehler ausgegeben und der Inhalt zurückgesetzt
*/
function kundenNummerOnFocusOut(){
    //Überprüfung ob Nummer eingegeben
    if(isNaN(kundennummer.value)){
            //Fehlermeldung ausgeben
            window.alert("Keine gültige Kundennummer eingegeben, versuchen Sie es erneut!");
            kundennummer.value = "";    //Wert zurücksetzen
            kundennummer.focus();       //Focus setzten - !Klappt leider nicht zuverlässig!
    }

    //Wenn das Feld verlassen wird prüfen ob es leer ist, wenn es leer ist andere Felder wieder zulassen
    if(kundennummer.valueAsNumber == 0 || kundennummer.value == ""){
        nachname.removeAttribute('disabled');
        vorname.removeAttribute('disabled');
        geburtsdatum.removeAttribute('disabled');
    }
}

/*
* EventListener für Nachname OnFocus
* Graut Felder aus, die nicht mit einer Eingabe in diesem feld kombinierbar sind
* Wenn nachname eingegeben wurde ist zusätzlich nur geburtsdatum erlaubt
*/
function nachnameOnFocus(){
    //Nur erlaubt, wenn außer geburtsadtum alles leer ist
    if(vorname.value == "" && (kundennummer.valueAsNumber == 0 || isNaN(kundennummer.valueAsNumber))){
        vorname.setAttribute("disabled", "true");
        kundennummer.setAttribute("disabled", "true");
        geburtsdatum.removeAttribute("disabled");
    }else if(kundennummer.valueAsNumber != 0){  //Fall Kundennummer ist eingetragen
        nachname.setAttribute("disabled", "true");  //hätte eigentlich deaktiviert sein sollen, wird nachgeholt
        //Wenn eine Kundennummer eingetragen ist sind andere Felder deaktiviert
        vorname.setAttribute("disabled", "true");
        geburtsdatum.removeAttribute("disabled");
    }else{ //Vorname ist eingetragen
        nachname.setAttribute("disabled", "true");      //hätte eigentlich deaktiviert sein sollen, wird nachgeholt
        kundennummer.setAttribute("disabled", "true");  //Wird deaktiviert sofern noch nicht passiert
        //Geburtsdatum und vorname sind eine gültige Kombination
        geburtsdatum.removeAttribute("disabled");
        vorname.removeAttribute("disabled");
    }
}

/*
* EventListener für Kundennummer OnFocusOut
* Wenn das Feld leer belibt werden die Felder, die vorher deaktieviert wurden aktiviert
*/
function nachnameOnFocusOut(){
    if(nachname.value == ""){
        vorname.removeAttribute("disabled");

        if(geburtsdatum.value == ""){
            kundennummer.removeAttribute("disabled");
            nachname.removeAttribute("disabled");
        }else{
            kundennummer.setAttribute("disabled", "true");  //Sollte ünerflüssig sein, wird zur Sicherheit aber nochmal gemacht
        }
    }
}

/*
* EventListener für vorname OnFocus
* Graut Felder aus, die nicht mit einer Eingabe in diesem feld kombinierbar sind
* Wenn vorname eingegeben wurde ist zusätzlich nur geburtsdatum erlaubt
*/
function vornameOnFocus(){
    //Nur erlaubt, wenn außer geburtsadtum alles leer ist
    if(nachname.value == "" && (kundennummer.valueAsNumber == 0 || isNaN(kundennummer.valueAsNumber))){
        nachname.setAttribute("disabled", "true");
        kundennummer.setAttribute("disabled", "true");
        geburtsdatum.removeAttribute("disabled");
    }else if(kundennummer.valueAsNumber != 0){  //Fall Kundennummer ist eingetragen
        vorname.setAttribute("disabled", "true");  //hätte eigentlich deaktiviert sein sollen, wird nachgeholt
        //Wenn eine Kundennummer eingetragen ist sind andere Felder deaktiviert
        nachname.setAttribute("disabled", "true");
        geburtsdatum.removeAttribute("disabled");
    }else{ //Nachname ist eingetragen
        vorname.setAttribute("disabled", "true");      //hätte eigentlich deaktiviert sein sollen, wird nachgeholt
        kundennummer.setAttribute("disabled", "true");  //Wird deaktiviert sofern noch nicht passiert
        //Geburtsdatum und vorname sind eine gültige Kombination
        geburtsdatum.removeAttribute("disabled");
        nachname.removeAttribute("disabled");
    }
}

/*
* EventListener für Kundennummer OnFocusOut
* Wenn das Feld leer belibt werden die Felder, die vorher deaktieviert wurden aktiviert
*/
function vornameOnFocusOut(){
    if(vorname.value == ""){
        nachname.removeAttribute("disabled");

        if(geburtsdatum.value == ""){
            kundennummer.removeAttribute("disabled");
            vorname.removeAttribute("disabled");
        }else{
            kundennummer.setAttribute("disabled", "true");  //Sollte übnerflüssig sein, wird zur Sicherheit aber nochmal gemacht
        }
    }
}

/*
* EventListener für geburtsdatum OnFocus
* Graut Felder aus, die nicht mit einer Eingabe in diesem feld kombinierbar sind
* Wenn geburtsdatum ausgewählt wurde ist zusätzlich nur nachname oder vorname erlaubt, wenn
*/
function geburtsdatumOnFocus(){
    kundennummer.setAttribute("disabled", "true");
    if(nachname.value == "") vorname.removeAttribute("disabled"); //Überflüssig aber zur sicherheit
    if(vorname.value == "") nachname.removeAttribute("disabled"); //Überflüssig aber zur sicherheit
}

/*
* EventListener für geburtsdatum OnFocusOut
*
*/
function geburtsdatumOnFocusOut(){
    if(nachname.value == "" && vorname.value == "" && geburtsdatum.value == "") kundennummer.removeAttribute("disabled");
}