async function deleteCar() {
	const id = document.getElementById("id").value;

	if (!id) {
		alert("Bitte geben Sie eine Auto-ID ein.");
		return;
	}

	try {
		const response = await deleteCarById(id);

		if (response.ok) {
			alert("Das Auto wurde erfolgreich gelöscht.");
			document.getElementById("auto-form").reset();
		} else if (response.status === 404) {
			alert("Auto-ID nicht gefunden. Bitte überprüfen Sie die eingegebene ID.");
		} else {
			alert("Fehler beim Löschen des Autos. Bitte versuchen Sie es später erneut.");
		}
	} catch (error) {
		console.error("Fehler beim Löschen des Autos:", error);
		alert("Fehler beim Löschen des Autos. Bitte versuchen Sie es später erneut.");
	}
}
