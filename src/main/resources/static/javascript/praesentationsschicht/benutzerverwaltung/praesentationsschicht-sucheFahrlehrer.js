function displaySearchResults(data) {
	let resultHtml = "";
	for (let i = 0; i < data.length; i++) {
		resultHtml += `<p class="search-result" data-index="${i}">${data[i].vorname} ${data[i].nachname}, 
               <strong>Geburtsdatum:</strong> ${new Date(data[i].geburtsdatum).toLocaleDateString()}, 
               <strong>Adresse:</strong> ${data[i].adresse.strasse} ${data[i].adresse.hausnummer}, ${data[i].adresse.plz} ${data[i].adresse.ort}</p>`;
	}
	document.getElementById("liste-schueler").innerHTML = resultHtml;

	const searchResults = document.getElementsByClassName("search-result");
	for (let i = 0; i < searchResults.length; i++) {
		searchResults[i].addEventListener("click", function() {
			const selectedIndex = this.getAttribute("data-index");
			const selectedInfo = `${data[selectedIndex].vorname} ${data[selectedIndex].nachname}, ${new Date(data[selectedIndex].geburtsdatum).toLocaleDateString()}, ${data[selectedIndex].adresse.strasse} ${data[selectedIndex].adresse.hausnummer}, ${data[selectedIndex].adresse.plz} ${data[selectedIndex].adresse.ort}`;
			document.getElementById("search-input").value = selectedInfo;
			document.getElementById("liste-schueler").innerHTML = "";
		});
	}
}