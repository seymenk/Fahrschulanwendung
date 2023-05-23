const listetermine = document.getElementById("liste-alle-termine");

function createTerminElement(termin) {
  const terminElement = document.createElement("div");
  terminElement.style.backgroundColor = termin.backgroundColor;
  terminElement.innerHTML = `<strong>Termin ID: ${termin.id}</strong>, <strong>FahrschuelerID:</strong> ${termin.fahrschuelerID}, <strong>FahrlehrerID:</strong> ${termin.fahrlehrerID}, <strong>Startuhrzeit:</strong> ${termin.startUhrzeit}, <strong>Enduhrzeit:</strong> ${termin.endUhrzeit}, <strong>Datum:</strong> ${termin.datum}, <strong>Gebucht:</strong> ${termin.gebucht}`;
  return terminElement;
}

function displayTermine(termine) {
  termine.forEach((termin) => {
    const terminElement = createTerminElement(termin);
    listetermine.appendChild(terminElement);
  });
}

function fetchAndDisplayTermine() {
  getTermine().then((termine) => {
    const processedTermine = processTermine(termine);
    displayTermine(processedTermine);
  });
}

fetchAndDisplayTermine();
