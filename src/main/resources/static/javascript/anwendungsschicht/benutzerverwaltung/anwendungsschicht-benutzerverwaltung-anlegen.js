function getUserFromFormData(formData) {
	return {
		vorname: formData.get("vorname"),
		nachname: formData.get("nachname"),
		geburtsdatum: formData.get("geburtstag"),
		adresse: {
			strasse: formData.get("strasse"),
			hausnummer: formData.get("hausnummer"),
			ort: formData.get("ort"),
			plz: parseInt(formData.get("plz")),
		},
	};
}

function checkRoleSelected(roleInputs) {
	let roleSelected = false;
	roleInputs.forEach((input) => {
		if (input.checked) roleSelected = true;
	});
	return roleSelected;
}
