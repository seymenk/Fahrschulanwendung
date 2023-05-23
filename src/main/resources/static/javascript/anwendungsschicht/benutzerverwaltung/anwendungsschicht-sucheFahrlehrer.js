document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("search-input").addEventListener("input", async function() {
		const input = this.value;
		const nameParts = input.split(" ").map(part => part.trim());
		const vorname = nameParts[0];
		const nachname = nameParts.length > 1 ? nameParts[1] : "";

		try {
			const data = await api.searchFahrlehrer(vorname, nachname);
			displaySearchResults(data);
		} catch (error) {
			console.error("Fehler bei der Suche nach Fahrlehrern:", error);
		}
	});
});