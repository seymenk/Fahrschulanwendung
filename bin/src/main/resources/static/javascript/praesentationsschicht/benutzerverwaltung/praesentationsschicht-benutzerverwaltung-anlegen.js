document.addEventListener("DOMContentLoaded", function() {
	const form = document.getElementById("user-form");
	const roleInputs = document.querySelectorAll('input[type="radio"]');

	form.addEventListener("submit", async function(event) {
		event.preventDefault();
		if (!checkRoleSelected(roleInputs)) {
			alert("Bitte wählen Sie eine Rolle aus.");
			return;
		}

		const formData = new FormData(form);
		const user = getUserFromFormData(formData);
		const role = formData.get("role");

		try {
			const response = await createUser(user, role);
			console.log("Server Response:", response);
			console.log("Response Status:", response.status);
			if (response.status === 200 || response.status === 201 || response === true) {
				alert("Benutzer erfolgreich erstellt.");
				form.reset();
			} else if (response.status === 400) {
				alert("Fehler beim Erstellen des Benutzers. Falsche Eingabe. Bitte versuchen Sie es erneut.");
			} else {
				alert("Fehler beim Erstellen des Benutzers. Bitte versuchen Sie es erneut.");
			}
		} catch (error) {
			alert("Fehler beim Erstellen des Benutzers. Bitte versuchen Sie es erneut.");
			console.error(error);
		}
	});
});
