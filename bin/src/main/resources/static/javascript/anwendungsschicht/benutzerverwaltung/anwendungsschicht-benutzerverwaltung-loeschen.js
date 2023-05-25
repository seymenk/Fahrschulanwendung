document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("user-form").addEventListener("submit", async function (event) {
        event.preventDefault();

        const benutzername = document.getElementById("benutzername").value;

        try {
            const response = await deleteUser(benutzername);

            if (response.ok) {
                alert("Benutzer erfolgreich gelöscht");
            } else {
                alert("Fehler beim Löschen des Benutzers");
            }
        } catch (error) {
            alert("Fehler beim Löschen des Benutzers");
        }
    });
});

async function deleteUser(benutzername) {
	return await api.deleteUser(benutzername);
}