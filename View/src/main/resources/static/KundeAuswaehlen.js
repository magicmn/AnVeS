var btnKundeAuswaehlen document.getElementById("kundeWaehlen");
var btnReservieren document.getElementById("reservieren")

btnReservieren.addAttribute("disabled", "true");

btnKundeAuswaehlen.onclick() = function(){
        btnReservieren.removeAttribute("disabled");
    }