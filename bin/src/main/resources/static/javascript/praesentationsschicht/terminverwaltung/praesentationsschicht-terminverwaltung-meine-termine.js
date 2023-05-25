const listetermine = document.getElementById("liste-meine-termine");

function createTerminElement(termin) {
  const terminElement = document.createElement("div");
  terminElement.style.backgroundColor = termin.backgroundColor;
  terminElement.innerHTML = `<strong>Termin ID: ${termin.id}</strong>, <strong>FahrschuelerID:</strong> ${termin.fahrschuelerID}, <strong>Startuhrzeit:</strong> ${termin.startUhrzeit}, <strong>Enduhrzeit:</strong> ${termin.endUhrzeit}, <strong>Datum:</strong> ${termin.datum}, <strong>Gebucht:</strong> ${termin.gebucht}`;
  return terminElement;
}

function displayMeineTermine(termine) {
  termine.forEach((termin) => {
    const terminElement = createTerminElement(termin);
    listetermine.appendChild(terminElement);
  });
}

function fetchAndDisplayMeineTermine() {
  getMeineTermine().then((termine) => {
    const processedTermine = processMeineTermine(termine);
    displayMeineTermine(processedTermine);
  });
}

fetchAndDisplayMeineTermine();
