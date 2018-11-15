// Javascript für KundenSuchen

//globale Variablen für Inputfelder setzten, um nicht jedes Mal über das DOM auf die Elemente zugreifen zu müssen
var kundennummer = document.getElementById("kundennummer");
var nachname = document.getElementById("nachname");
var vorname = document.getElementById("vorname");
var geburtsdatum = document.getElementById("geburtsdatum");

kundennummer.addEventListener("change", kundennummerOnChange);
kundennummer.addEventListener("focus", kundenNummerOnFocus);
kundennummer.addEventListener("focusout", kundenNummerOnFocusOut);

document.getElementById("nachname").addEventListener("change", nameOnChange);
document.getElementById("vorname").addEventListener("change", vornameOnChange);
document.getElementById("geburtsdatum").addEventListener("change", geburtsdatumOnChange);

function kundenNummerOnFocus(){
    if(nachname.value == "" && vorname.value == "" && geburtsdatum.value == ""){
        nachname.setAttribute('disabled', 'true');
        vorname.setAttribute('disabled', 'true');
        geburtsdatum.setAttribute('disabled', 'true');
    }
}

function kundenNummerOnFocusOut(){
    if(kundennummer.valueAsNumber == 0 || kundennummer.value == ""){
        nachname.setAttribute('disabled', 'false');
        vorname.setAttribute('disabled', 'false');
        geburtsdatum.setAttribute('disabled', 'false');
    }
}

function kundennummerOnChange() {
    window.alert("kundenNummerOnChange");
}

function nameOnChange() {
    window.alert("nameOnChange");
}

function vornameOnChange() {
    window.alert("vornameOnChange");
}

function geburtsdatumOnChange() {
    window.alert("geburtsdatumOnChange");
}
