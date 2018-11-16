var zeitraumVon = document.getElementById("zeitraumVon");
var zeitraumBis = document.getElementById("zeitraumBis");
var tage = document.getElementById("tage");
var submitButton = document.getElementById("submitButton");

tage.addEventListener("blur", resolveDate);
tage.addEventListener("blur", resolveSubmitEnabled);
zeitraumVon.addEventListener("blur", resolveDays);
zeitraumBis.addEventListener("blur", resolveDays);
zeitraumVon.addEventListener("blur", resolveSubmitEnabled);
zeitraumBis.addEventListener("blur", resolveSubmitEnabled);

function resolveSubmitEnabled() {
    if(zeitraumVon.value && zeitraumBis.value && moment(zeitraumBis.value).diff(moment(zeitraumVon.value), "days") >= 0) {
        submitButton.removeAttribute("disabled");
    } else {
        submitButton.setAttribute("disabled", "true");
    }
}

function resolveDate() {
    if(tage.value) {
        if(zeitraumVon.value) {
            zeitraumBis.value = moment(zeitraumVon.value).add(tage.value - 1, "days").format(moment.HTML5_FMT.DATE);
        } else if(zeitraumBis.value) {
            zeitraumVon.value = moment(zeitraumBis.value).subtract(tage.value, "days").add(1, "days").format(moment.HTML5_FMT.DATE);
        }
    }
}

function resolveDays() {
    if(zeitraumVon.value && zeitraumBis.value) {
        tage.value = 1 + moment(zeitraumBis.value).diff(moment(zeitraumVon.value), "days");
    } else if(zeitraumVon.value && tage.value) {
        zeitraumBis.value = moment(zeitraumVon.value).add(tage.value - 1, "days").format(moment.HTML5_FMT.DATE);
    } else if(zeitraumBis.value && tage.value) {
        zeitraumVon.value = moment(zeitraumBis.value).subtract(tage.value, "days").add(1, "days").format(moment.HTML5_FMT.DATE);
    }
}