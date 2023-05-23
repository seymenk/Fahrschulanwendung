const api = {
	async deleteUser(benutzername) {
		try {
			const response = await fetch(`/benutzer/${benutzername}`, {
				method: "DELETE",
			});
			return response;
		} catch (error) {
			throw new Error("Fehler beim Löschen des Benutzers");
		}
	},
};