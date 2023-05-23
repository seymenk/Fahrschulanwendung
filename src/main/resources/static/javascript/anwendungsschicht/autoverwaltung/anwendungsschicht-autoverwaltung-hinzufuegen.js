async function submitAuto(autoData) {
	const auto = {
		marke: autoData.marke,
		modell: autoData.modell,
		baujahr: autoData.baujahr,
		farbe: autoData.farbe,
		preis: autoData.preis,
		"@class": autoData.getriebe === "Manuell"
			? "softwaretechnik2.fahrschule.swt.autoverwaltung.ManuellDTO"
			: "softwaretechnik2.fahrschule.swt.autoverwaltung.AutomatikDTO",
	};

	const response = await createAuto(auto);

	if (!response.ok) {
		throw new Error("Fehler beim Hinzufügen des Autos.");
	}

	return response;
}
