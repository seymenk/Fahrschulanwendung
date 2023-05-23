function validateTerminId(id) {
	if (!id) {
		return {
			valid: false,
			message: "Bitte geben Sie eine Termin-ID ein.",
		};
	}

	return {
		valid: true,
	};
}

async function onDeleteTermin(event) {
	event.preventDefault();

	const id = document.getElementById("id").value;
	const validationResult = validateTerminId(id);

	if (!validationResult.valid) {
		alert(validationResult.message);
		return;
	}

	try {
		await deleteTerminById(id);
		alert("Der Termin wurde erfolgreich gelöscht.");
		document.getElementById("termin-form").reset();
	} catch (error) {
		if (error.status === 404) {
			alert("Termin-ID nicht gefunden. Bitte überprüfen Sie die eingegebene ID.");
		} else {
			alert("Fehler beim Löschen des Termins. Bitte versuchen Sie es später erneut.");
		}
	}
}

document.getElementById("termin-form").addEventListener("submit", onDeleteTermin);
