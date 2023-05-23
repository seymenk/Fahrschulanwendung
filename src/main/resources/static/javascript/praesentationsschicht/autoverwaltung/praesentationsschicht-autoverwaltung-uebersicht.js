document.addEventListener("DOMContentLoaded", function() {
    fetchCars();
});

function displayCars(cars) {
    const listeAutos = document.getElementById('liste-autos');
    
    cars.forEach(auto => {
        const autoElement = document.createElement('div');
        autoElement.innerHTML = `<strong>Auto ID: ${auto.id}</strong>, <strong>Marke:</strong> ${auto.marke}, <strong>Modell:</strong> ${auto.modell}, <strong>Baujahr:</strong> ${auto.baujahr}, <strong>Farbe:</strong> ${auto.farbe}, <strong>Getriebe:</strong> ${auto['@class'] === "softwaretechnik2.fahrschule.swt.autoverwaltung.AutomatikDTO" ? "Automatik" : "Manuell"}, <strong>Preis:</strong> ${auto.preis}€`;
        listeAutos.appendChild(autoElement);
    });
}
