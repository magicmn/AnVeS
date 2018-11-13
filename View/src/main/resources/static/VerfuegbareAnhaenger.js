var lastCollapseComponentId = "";

/* Beim aufrufen der Funktion wird mithilfe der Id (oder Klasse) das spezifierte Element
/* eingeblendet und das vorher eingeblendete Element ausgeblendet. Damit das funktioniert muss
/* es sich um ein Element mit der Klasse "collapse" handeln. */
function toggleCollapse(enableComponentId) {
    try {
        $(lastCollapseComponentId).collapse("hide");
    } catch (error) {}
    $(enableComponentId).collapse("show");
    lastCollapseComponentId=enableComponentId;
}