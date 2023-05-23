document.addEventListener("DOMContentLoaded", function() {
	const form = document.getElementById("auto-form");
	const roleInputs = document.querySelectorAll('input[type="radio"]');

	function checkRoleSelected() {
		let roleSelected = false;
		roleInputs.forEach((input) => {
			if (input.checked) roleSelected = true;
		});
		return roleSelected;
	}

	form.addEventListener("submit", function(event) {
		event.preventDefault();
		if (!checkRoleSelected()) {
			alert("Bitte wählen Sie eine Getriebeart aus.");
			return;
		}

		const formData = new FormData(form);
		const autoData = {
			marke: formData.get("marke"),
			modell: formData.get("modell"),
			baujahr: parseInt(formData.get("baujahr"), 10),
			farbe: formData.get("farbe"),
			preis: parseFloat(formData.get("preis")),
			getriebe: formData.get("role"),
		};

		submitAuto(autoData)
			.then((response) => {
				alert("Auto erfolgreich hinzugefügt.");
				form.reset();
			})
			.catch((error) => {
				alert("Fehler beim Hinzufügen des Autos. Bitte versuchen Sie es erneut.");
				console.error(error);
			});
	});
});
