document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("search-input").addEventListener("input", async function() {
		const input = this.value;
		const nameParts = input.split(" ").map(part => part.trim());
		const vorname = nameParts[0];
		const nachname = nameParts.length > 1 ? nameParts[1] : "";

		try {
			const data = await api.searchFahrschueler(vorname, nachname);
			displaySearchResults(data);
		} catch (error) {
			console.error("Fehler bei der Suche nach Fahrschülern:", error);
		}
	});
});

function displaySearchResults(data) {
	let resultHtml = "";
	for (let i = 0; i < data.length; i++) {
		resultHtml += `<p class="search-result" data-index="${i}">${data[i].vorname} ${data[i].nachname}, 
               <strong>Geburtsdatum:</strong> ${new Date(data[i].geburtsdatum).toLocaleDateString()}, 
               <strong>Adresse:</strong> ${data[i].adresseStrasse} ${data[i].adresseHausnummer}, ${data[i].adressePlz} ${data[i].adresseOrt}</p>`;
	}
	document.getElementById("liste-schueler").innerHTML = resultHtml;

	const searchResults = document.getElementsByClassName("search-result");
	for (let i = 0; i < searchResults.length; i++) {
		searchResults[i].addEventListener("click", function() {
			const selectedIndex = this.getAttribute("data-index");
			const selectedInfo = `${data[selectedIndex].vorname} ${data[selectedIndex].nachname}, ${new Date(data[selectedIndex].geburtsdatum).toLocaleDateString()}, ${data[selectedIndex].adresseStrasse} ${data[selectedIndex].adresseHausnummer}, ${data[selectedIndex].adressePlz} ${data[selectedIndex].adresseOrt}`;
			document.getElementById("search-input").value = selectedInfo;
			document.getElementById("liste-schueler").innerHTML = "";
		});
	}
}