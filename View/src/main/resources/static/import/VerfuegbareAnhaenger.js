var lastCollapseComponentId = "";

function toggleCollapse(enableComponentId) {
    try {
        $(lastCollapseComponentId).collapse("hide");
    } catch (error) {}
    $(enableComponentId).collapse("show");
    lastCollapseComponentId=enableComponentId;
}

function toggleCollapseX(enableComponentId) {
    try {
    document.getElementById(lastCollapseComponentId).classList.remove("show");
    } catch (error) {}
    document.getElementById(enableComponentId).classList.add("show");
    lastCollapseComponentId=enableComponentId;
}