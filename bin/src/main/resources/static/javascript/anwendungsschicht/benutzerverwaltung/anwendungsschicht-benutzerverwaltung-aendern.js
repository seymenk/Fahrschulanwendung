function init() {
	document.getElementById('user-form').addEventListener('submit', async function(event) {
		event.preventDefault();

		const benutzername = document.getElementById('benutzername').value;
		const altes_passwort = document.getElementById('altes_passwort').value;
		const neues_passwort = document.getElementById('neues_passwort').value;

		try {
			const response = await changePassword(benutzername, altes_passwort, neues_passwort);
			if (response) {
				alert('Passwort erfolgreich geändert');
			} else {
				alert('Fehler beim Ändern des Passworts');
			}
		} catch (error) {
			alert('Fehler beim Ändern des Passworts');
		}
	});
}

document.addEventListener("DOMContentLoaded", init);
