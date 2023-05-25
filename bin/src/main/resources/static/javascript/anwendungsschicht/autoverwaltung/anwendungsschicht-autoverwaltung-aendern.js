async function submitForm(event) {
	event.preventDefault();
	const form = document.getElementById("auto-form");

	const formData = new FormData(form);
	const id = formData.get("id");

	try {
		const currentCarData = await getCarData(id);
		const auto = createCarDataObject(formData, currentCarData);
		const response = await changeCarData(auto);

		if (response.ok) {
			alert("Auto erfolgreich geändert.");
			form.reset();
		} else {
			alert("Fehler beim Ändern des Autos. Bitte versuchen Sie es erneut.");
		}
	} catch (error) {
		alert("Fehler beim Ändern des Autos. Bitte versuchen Sie es erneut.");
		console.error(error);
	}
}

function createCarDataObject(formData, currentCarData) {
	const marke = formData.get("marke") || currentCarData.marke;
	const modell = formData.get("modell") || currentCarData.modell;
	const baujahr = formData.get("baujahr") || currentCarData.baujahr;
	const farbe = formData.get("farbe") || currentCarData.farbe;
	const preis = formData.get("preis") || currentCarData.preis;

	return {
		id: formData.get("id"),
		marke: marke,
		modell: modell,
		baujahr: baujahr,
		farbe: farbe,
		preis: preis,
		"@class": currentCarData["@class"]
	};
}
